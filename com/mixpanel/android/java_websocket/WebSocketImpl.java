package com.mixpanel.android.java_websocket;

import android.annotation.SuppressLint;
import com.mixpanel.android.java_websocket.drafts.Draft;
import com.mixpanel.android.java_websocket.drafts.Draft.CloseHandshakeType;
import com.mixpanel.android.java_websocket.drafts.Draft.HandshakeState;
import com.mixpanel.android.java_websocket.drafts.Draft_10;
import com.mixpanel.android.java_websocket.drafts.Draft_17;
import com.mixpanel.android.java_websocket.drafts.Draft_75;
import com.mixpanel.android.java_websocket.drafts.Draft_76;
import com.mixpanel.android.java_websocket.exceptions.IncompleteHandshakeException;
import com.mixpanel.android.java_websocket.exceptions.InvalidDataException;
import com.mixpanel.android.java_websocket.exceptions.InvalidHandshakeException;
import com.mixpanel.android.java_websocket.exceptions.WebsocketNotConnectedException;
import com.mixpanel.android.java_websocket.framing.CloseFrame;
import com.mixpanel.android.java_websocket.framing.CloseFrameBuilder;
import com.mixpanel.android.java_websocket.framing.Framedata;
import com.mixpanel.android.java_websocket.framing.Framedata.Opcode;
import com.mixpanel.android.java_websocket.handshake.ClientHandshake;
import com.mixpanel.android.java_websocket.handshake.ClientHandshakeBuilder;
import com.mixpanel.android.java_websocket.handshake.Handshakedata;
import com.mixpanel.android.java_websocket.server.WebSocketServer.WebSocketWorker;
import com.mixpanel.android.java_websocket.util.Charsetfunctions;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@SuppressLint({"Assert"})
public class WebSocketImpl
  implements WebSocket
{
  public static boolean DEBUG;
  public static int RCVBUF;
  public static final List<Draft> defaultdraftlist;
  public ByteChannel channel;
  private Integer closecode = null;
  private Boolean closedremotely = null;
  private String closemessage = null;
  private Framedata.Opcode current_continuous_frame_opcode = null;
  private Draft draft = null;
  private volatile boolean flushandclosestate = false;
  private ClientHandshake handshakerequest = null;
  public final BlockingQueue<ByteBuffer> inQueue;
  public SelectionKey key;
  private List<Draft> knownDrafts;
  public final BlockingQueue<ByteBuffer> outQueue;
  private WebSocket.READYSTATE readystate = WebSocket.READYSTATE.NOT_YET_CONNECTED;
  private String resourceDescriptor = null;
  private WebSocket.Role role;
  private ByteBuffer tmpHandshakeBytes = ByteBuffer.allocate(0);
  public volatile WebSocketServer.WebSocketWorker workerThread;
  private final WebSocketListener wsl;
  
  static
  {
    if (!WebSocketImpl.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      RCVBUF = 16384;
      DEBUG = false;
      defaultdraftlist = new ArrayList(4);
      defaultdraftlist.add(new Draft_17());
      defaultdraftlist.add(new Draft_10());
      defaultdraftlist.add(new Draft_76());
      defaultdraftlist.add(new Draft_75());
      return;
    }
  }
  
  public WebSocketImpl(WebSocketListener paramWebSocketListener, Draft paramDraft)
  {
    if ((paramWebSocketListener == null) || ((paramDraft == null) && (this.role == WebSocket.Role.SERVER))) {
      throw new IllegalArgumentException("parameters must not be null");
    }
    this.outQueue = new LinkedBlockingQueue();
    this.inQueue = new LinkedBlockingQueue();
    this.wsl = paramWebSocketListener;
    this.role = WebSocket.Role.CLIENT;
    if (paramDraft != null) {
      this.draft = paramDraft.copyInstance();
    }
  }
  
  @Deprecated
  public WebSocketImpl(WebSocketListener paramWebSocketListener, Draft paramDraft, Socket paramSocket)
  {
    this(paramWebSocketListener, paramDraft);
  }
  
  public WebSocketImpl(WebSocketListener paramWebSocketListener, List<Draft> paramList)
  {
    this(paramWebSocketListener, (Draft)null);
    this.role = WebSocket.Role.SERVER;
    if ((paramList == null) || (paramList.isEmpty()))
    {
      this.knownDrafts = defaultdraftlist;
      return;
    }
    this.knownDrafts = paramList;
  }
  
  @Deprecated
  public WebSocketImpl(WebSocketListener paramWebSocketListener, List<Draft> paramList, Socket paramSocket)
  {
    this(paramWebSocketListener, paramList);
  }
  
  private void close(int paramInt, String paramString, boolean paramBoolean)
  {
    if ((this.readystate != WebSocket.READYSTATE.CLOSING) && (this.readystate != WebSocket.READYSTATE.CLOSED))
    {
      if (this.readystate != WebSocket.READYSTATE.OPEN) {
        break label190;
      }
      if (paramInt == 1006)
      {
        assert (!paramBoolean);
        this.readystate = WebSocket.READYSTATE.CLOSING;
        flushAndClose(paramInt, paramString, false);
      }
    }
    else
    {
      return;
    }
    if ((this.draft.getCloseHandshakeType() == Draft.CloseHandshakeType.NONE) || (!paramBoolean)) {}
    for (;;)
    {
      try
      {
        this.wsl.onWebsocketCloseInitiated(this, paramInt, paramString);
        sendFrame(new CloseFrameBuilder(paramInt, paramString));
        flushAndClose(paramInt, paramString, paramBoolean);
        if (paramInt == 1002) {
          flushAndClose(paramInt, paramString, paramBoolean);
        }
        this.readystate = WebSocket.READYSTATE.CLOSING;
        this.tmpHandshakeBytes = null;
        return;
      }
      catch (RuntimeException localRuntimeException)
      {
        this.wsl.onWebsocketError(this, localRuntimeException);
        continue;
      }
      catch (InvalidDataException localInvalidDataException)
      {
        this.wsl.onWebsocketError(this, localInvalidDataException);
        flushAndClose(1006, "generated frame is invalid", false);
        continue;
      }
      label190:
      if (paramInt == -3)
      {
        assert (paramBoolean);
        flushAndClose(-3, paramString, true);
      }
      else
      {
        flushAndClose(-1, paramString, false);
      }
    }
  }
  
  private void decodeFrames(ByteBuffer paramByteBuffer)
  {
    for (;;)
    {
      Framedata localFramedata;
      boolean bool;
      int i;
      try
      {
        Iterator localIterator = this.draft.translateFrame(paramByteBuffer).iterator();
        if (localIterator.hasNext())
        {
          localFramedata = (Framedata)localIterator.next();
          if (DEBUG) {
            System.out.println("matched frame: " + localFramedata);
          }
          paramByteBuffer = localFramedata.getOpcode();
          bool = localFramedata.isFin();
          if (paramByteBuffer != Framedata.Opcode.CLOSING) {
            break label200;
          }
          i = 1005;
          paramByteBuffer = "";
          if ((localFramedata instanceof CloseFrame))
          {
            paramByteBuffer = (CloseFrame)localFramedata;
            i = paramByteBuffer.getCloseCode();
            paramByteBuffer = paramByteBuffer.getMessage();
          }
          if (this.readystate == WebSocket.READYSTATE.CLOSING) {
            closeConnection(i, paramByteBuffer, true);
          }
        }
        else
        {
          return;
        }
      }
      catch (InvalidDataException paramByteBuffer)
      {
        this.wsl.onWebsocketError(this, paramByteBuffer);
        close(paramByteBuffer);
      }
      if (this.draft.getCloseHandshakeType() == Draft.CloseHandshakeType.TWOWAY)
      {
        close(i, paramByteBuffer, true);
      }
      else
      {
        flushAndClose(i, paramByteBuffer, false);
        continue;
        label200:
        if (paramByteBuffer == Framedata.Opcode.PING)
        {
          this.wsl.onWebsocketPing(this, localFramedata);
        }
        else if (paramByteBuffer == Framedata.Opcode.PONG)
        {
          this.wsl.onWebsocketPong(this, localFramedata);
        }
        else
        {
          if ((!bool) || (paramByteBuffer == Framedata.Opcode.CONTINUOUS))
          {
            if (paramByteBuffer != Framedata.Opcode.CONTINUOUS)
            {
              if (this.current_continuous_frame_opcode != null) {
                throw new InvalidDataException(1002, "Previous continuous frame sequence not completed.");
              }
              this.current_continuous_frame_opcode = paramByteBuffer;
            }
            label351:
            do
            {
              for (;;)
              {
                try
                {
                  this.wsl.onWebsocketMessageFragment(this, localFramedata);
                }
                catch (RuntimeException paramByteBuffer)
                {
                  this.wsl.onWebsocketError(this, paramByteBuffer);
                }
                break;
                if (!bool) {
                  break label351;
                }
                if (this.current_continuous_frame_opcode == null) {
                  throw new InvalidDataException(1002, "Continuous frame sequence was not started.");
                }
                this.current_continuous_frame_opcode = null;
              }
            } while (this.current_continuous_frame_opcode != null);
            throw new InvalidDataException(1002, "Continuous frame sequence was not started.");
          }
          if (this.current_continuous_frame_opcode != null) {
            throw new InvalidDataException(1002, "Continuous frame sequence not completed.");
          }
          Framedata.Opcode localOpcode = Framedata.Opcode.TEXT;
          if (paramByteBuffer == localOpcode)
          {
            try
            {
              this.wsl.onWebsocketMessage(this, Charsetfunctions.stringUtf8(localFramedata.getPayloadData()));
            }
            catch (RuntimeException paramByteBuffer)
            {
              this.wsl.onWebsocketError(this, paramByteBuffer);
            }
          }
          else
          {
            localOpcode = Framedata.Opcode.BINARY;
            if (paramByteBuffer != localOpcode) {
              break;
            }
            try
            {
              this.wsl.onWebsocketMessage(this, localFramedata.getPayloadData());
            }
            catch (RuntimeException paramByteBuffer)
            {
              this.wsl.onWebsocketError(this, paramByteBuffer);
            }
          }
        }
      }
    }
    throw new InvalidDataException(1002, "non control or continious frame expected");
  }
  
  /* Error */
  private boolean decodeHandshake(ByteBuffer paramByteBuffer)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 115	com/mixpanel/android/java_websocket/WebSocketImpl:tmpHandshakeBytes	Ljava/nio/ByteBuffer;
    //   4: invokevirtual 367	java/nio/ByteBuffer:capacity	()I
    //   7: ifne +70 -> 77
    //   10: aload_1
    //   11: astore 4
    //   13: aload 4
    //   15: invokevirtual 371	java/nio/ByteBuffer:mark	()Ljava/nio/Buffer;
    //   18: pop
    //   19: aload_0
    //   20: getfield 105	com/mixpanel/android/java_websocket/WebSocketImpl:draft	Lcom/mixpanel/android/java_websocket/drafts/Draft;
    //   23: ifnonnull +207 -> 230
    //   26: aload_0
    //   27: aload 4
    //   29: invokespecial 375	com/mixpanel/android/java_websocket/WebSocketImpl:isFlashEdgeCase	(Ljava/nio/ByteBuffer;)Lcom/mixpanel/android/java_websocket/drafts/Draft$HandshakeState;
    //   32: astore 5
    //   34: getstatic 381	com/mixpanel/android/java_websocket/drafts/Draft$HandshakeState:MATCHED	Lcom/mixpanel/android/java_websocket/drafts/Draft$HandshakeState;
    //   37: astore 6
    //   39: aload 5
    //   41: aload 6
    //   43: if_acmpne +187 -> 230
    //   46: aload_0
    //   47: aload_0
    //   48: getfield 148	com/mixpanel/android/java_websocket/WebSocketImpl:wsl	Lcom/mixpanel/android/java_websocket/WebSocketListener;
    //   51: aload_0
    //   52: invokeinterface 385 2 0
    //   57: invokestatic 389	com/mixpanel/android/java_websocket/util/Charsetfunctions:utf8Bytes	(Ljava/lang/String;)[B
    //   60: invokestatic 393	java/nio/ByteBuffer:wrap	([B)Ljava/nio/ByteBuffer;
    //   63: invokespecial 396	com/mixpanel/android/java_websocket/WebSocketImpl:write	(Ljava/nio/ByteBuffer;)V
    //   66: aload_0
    //   67: bipush -3
    //   69: ldc_w 287
    //   72: invokevirtual 398	com/mixpanel/android/java_websocket/WebSocketImpl:close	(ILjava/lang/String;)V
    //   75: iconst_0
    //   76: ireturn
    //   77: aload_0
    //   78: getfield 115	com/mixpanel/android/java_websocket/WebSocketImpl:tmpHandshakeBytes	Ljava/nio/ByteBuffer;
    //   81: invokevirtual 401	java/nio/ByteBuffer:remaining	()I
    //   84: aload_1
    //   85: invokevirtual 401	java/nio/ByteBuffer:remaining	()I
    //   88: if_icmpge +44 -> 132
    //   91: aload_0
    //   92: getfield 115	com/mixpanel/android/java_websocket/WebSocketImpl:tmpHandshakeBytes	Ljava/nio/ByteBuffer;
    //   95: invokevirtual 367	java/nio/ByteBuffer:capacity	()I
    //   98: aload_1
    //   99: invokevirtual 401	java/nio/ByteBuffer:remaining	()I
    //   102: iadd
    //   103: invokestatic 113	java/nio/ByteBuffer:allocate	(I)Ljava/nio/ByteBuffer;
    //   106: astore 4
    //   108: aload_0
    //   109: getfield 115	com/mixpanel/android/java_websocket/WebSocketImpl:tmpHandshakeBytes	Ljava/nio/ByteBuffer;
    //   112: invokevirtual 404	java/nio/ByteBuffer:flip	()Ljava/nio/Buffer;
    //   115: pop
    //   116: aload 4
    //   118: aload_0
    //   119: getfield 115	com/mixpanel/android/java_websocket/WebSocketImpl:tmpHandshakeBytes	Ljava/nio/ByteBuffer;
    //   122: invokevirtual 408	java/nio/ByteBuffer:put	(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;
    //   125: pop
    //   126: aload_0
    //   127: aload 4
    //   129: putfield 115	com/mixpanel/android/java_websocket/WebSocketImpl:tmpHandshakeBytes	Ljava/nio/ByteBuffer;
    //   132: aload_0
    //   133: getfield 115	com/mixpanel/android/java_websocket/WebSocketImpl:tmpHandshakeBytes	Ljava/nio/ByteBuffer;
    //   136: aload_1
    //   137: invokevirtual 408	java/nio/ByteBuffer:put	(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;
    //   140: pop
    //   141: aload_0
    //   142: getfield 115	com/mixpanel/android/java_websocket/WebSocketImpl:tmpHandshakeBytes	Ljava/nio/ByteBuffer;
    //   145: invokevirtual 404	java/nio/ByteBuffer:flip	()Ljava/nio/Buffer;
    //   148: pop
    //   149: aload_0
    //   150: getfield 115	com/mixpanel/android/java_websocket/WebSocketImpl:tmpHandshakeBytes	Ljava/nio/ByteBuffer;
    //   153: astore 4
    //   155: goto -142 -> 13
    //   158: astore 5
    //   160: aload_0
    //   161: sipush 1006
    //   164: ldc_w 410
    //   167: iconst_1
    //   168: invokespecial 307	com/mixpanel/android/java_websocket/WebSocketImpl:close	(ILjava/lang/String;Z)V
    //   171: goto -96 -> 75
    //   174: astore 5
    //   176: aload_0
    //   177: getfield 115	com/mixpanel/android/java_websocket/WebSocketImpl:tmpHandshakeBytes	Ljava/nio/ByteBuffer;
    //   180: invokevirtual 367	java/nio/ByteBuffer:capacity	()I
    //   183: ifne +606 -> 789
    //   186: aload 4
    //   188: invokevirtual 413	java/nio/ByteBuffer:reset	()Ljava/nio/Buffer;
    //   191: pop
    //   192: aload 5
    //   194: invokevirtual 416	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException:getPreferedSize	()I
    //   197: istore_3
    //   198: iload_3
    //   199: ifne +559 -> 758
    //   202: aload 4
    //   204: invokevirtual 367	java/nio/ByteBuffer:capacity	()I
    //   207: bipush 16
    //   209: iadd
    //   210: istore_2
    //   211: aload_0
    //   212: iload_2
    //   213: invokestatic 113	java/nio/ByteBuffer:allocate	(I)Ljava/nio/ByteBuffer;
    //   216: putfield 115	com/mixpanel/android/java_websocket/WebSocketImpl:tmpHandshakeBytes	Ljava/nio/ByteBuffer;
    //   219: aload_0
    //   220: getfield 115	com/mixpanel/android/java_websocket/WebSocketImpl:tmpHandshakeBytes	Ljava/nio/ByteBuffer;
    //   223: aload_1
    //   224: invokevirtual 408	java/nio/ByteBuffer:put	(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;
    //   227: pop
    //   228: iconst_0
    //   229: ireturn
    //   230: aload_0
    //   231: getfield 127	com/mixpanel/android/java_websocket/WebSocketImpl:role	Lcom/mixpanel/android/java_websocket/WebSocket$Role;
    //   234: getstatic 132	com/mixpanel/android/java_websocket/WebSocket$Role:SERVER	Lcom/mixpanel/android/java_websocket/WebSocket$Role;
    //   237: if_acmpne +314 -> 551
    //   240: aload_0
    //   241: getfield 105	com/mixpanel/android/java_websocket/WebSocketImpl:draft	Lcom/mixpanel/android/java_websocket/drafts/Draft;
    //   244: ifnonnull +233 -> 477
    //   247: aload_0
    //   248: getfield 168	com/mixpanel/android/java_websocket/WebSocketImpl:knownDrafts	Ljava/util/List;
    //   251: invokeinterface 236 1 0
    //   256: astore 5
    //   258: aload 5
    //   260: invokeinterface 241 1 0
    //   265: ifeq +192 -> 457
    //   268: aload 5
    //   270: invokeinterface 245 1 0
    //   275: checkcast 153	com/mixpanel/android/java_websocket/drafts/Draft
    //   278: invokevirtual 157	com/mixpanel/android/java_websocket/drafts/Draft:copyInstance	()Lcom/mixpanel/android/java_websocket/drafts/Draft;
    //   281: astore 6
    //   283: aload 6
    //   285: aload_0
    //   286: getfield 127	com/mixpanel/android/java_websocket/WebSocketImpl:role	Lcom/mixpanel/android/java_websocket/WebSocket$Role;
    //   289: invokevirtual 420	com/mixpanel/android/java_websocket/drafts/Draft:setParseMode	(Lcom/mixpanel/android/java_websocket/WebSocket$Role;)V
    //   292: aload 4
    //   294: invokevirtual 413	java/nio/ByteBuffer:reset	()Ljava/nio/Buffer;
    //   297: pop
    //   298: aload 6
    //   300: aload 4
    //   302: invokevirtual 424	com/mixpanel/android/java_websocket/drafts/Draft:translateHandshake	(Ljava/nio/ByteBuffer;)Lcom/mixpanel/android/java_websocket/handshake/Handshakedata;
    //   305: astore 7
    //   307: aload 7
    //   309: instanceof 426
    //   312: ifne +16 -> 328
    //   315: aload_0
    //   316: sipush 1002
    //   319: ldc_w 428
    //   322: iconst_0
    //   323: invokevirtual 195	com/mixpanel/android/java_websocket/WebSocketImpl:flushAndClose	(ILjava/lang/String;Z)V
    //   326: iconst_0
    //   327: ireturn
    //   328: aload 7
    //   330: checkcast 426	com/mixpanel/android/java_websocket/handshake/ClientHandshake
    //   333: astore 7
    //   335: aload 6
    //   337: aload 7
    //   339: invokevirtual 432	com/mixpanel/android/java_websocket/drafts/Draft:acceptHandshakeAsServer	(Lcom/mixpanel/android/java_websocket/handshake/ClientHandshake;)Lcom/mixpanel/android/java_websocket/drafts/Draft$HandshakeState;
    //   342: getstatic 381	com/mixpanel/android/java_websocket/drafts/Draft$HandshakeState:MATCHED	Lcom/mixpanel/android/java_websocket/drafts/Draft$HandshakeState;
    //   345: if_acmpne -87 -> 258
    //   348: aload_0
    //   349: aload 7
    //   351: invokeinterface 435 1 0
    //   356: putfield 125	com/mixpanel/android/java_websocket/WebSocketImpl:resourceDescriptor	Ljava/lang/String;
    //   359: aload_0
    //   360: getfield 148	com/mixpanel/android/java_websocket/WebSocketImpl:wsl	Lcom/mixpanel/android/java_websocket/WebSocketListener;
    //   363: aload_0
    //   364: aload 6
    //   366: aload 7
    //   368: invokeinterface 439 4 0
    //   373: astore 8
    //   375: aload_0
    //   376: aload 6
    //   378: aload 6
    //   380: aload 7
    //   382: aload 8
    //   384: invokevirtual 443	com/mixpanel/android/java_websocket/drafts/Draft:postProcessHandshakeResponseAsServer	(Lcom/mixpanel/android/java_websocket/handshake/ClientHandshake;Lcom/mixpanel/android/java_websocket/handshake/ServerHandshakeBuilder;)Lcom/mixpanel/android/java_websocket/handshake/HandshakeBuilder;
    //   387: aload_0
    //   388: getfield 127	com/mixpanel/android/java_websocket/WebSocketImpl:role	Lcom/mixpanel/android/java_websocket/WebSocket$Role;
    //   391: invokevirtual 447	com/mixpanel/android/java_websocket/drafts/Draft:createHandshake	(Lcom/mixpanel/android/java_websocket/handshake/Handshakedata;Lcom/mixpanel/android/java_websocket/WebSocket$Role;)Ljava/util/List;
    //   394: invokespecial 450	com/mixpanel/android/java_websocket/WebSocketImpl:write	(Ljava/util/List;)V
    //   397: aload_0
    //   398: aload 6
    //   400: putfield 105	com/mixpanel/android/java_websocket/WebSocketImpl:draft	Lcom/mixpanel/android/java_websocket/drafts/Draft;
    //   403: aload_0
    //   404: aload 7
    //   406: invokespecial 454	com/mixpanel/android/java_websocket/WebSocketImpl:open	(Lcom/mixpanel/android/java_websocket/handshake/Handshakedata;)V
    //   409: iconst_1
    //   410: ireturn
    //   411: astore 6
    //   413: aload_0
    //   414: aload 6
    //   416: invokevirtual 455	com/mixpanel/android/java_websocket/exceptions/InvalidDataException:getCloseCode	()I
    //   419: aload 6
    //   421: invokevirtual 456	com/mixpanel/android/java_websocket/exceptions/InvalidDataException:getMessage	()Ljava/lang/String;
    //   424: iconst_0
    //   425: invokevirtual 195	com/mixpanel/android/java_websocket/WebSocketImpl:flushAndClose	(ILjava/lang/String;Z)V
    //   428: iconst_0
    //   429: ireturn
    //   430: astore 6
    //   432: aload_0
    //   433: getfield 148	com/mixpanel/android/java_websocket/WebSocketImpl:wsl	Lcom/mixpanel/android/java_websocket/WebSocketListener;
    //   436: aload_0
    //   437: aload 6
    //   439: invokeinterface 224 3 0
    //   444: aload_0
    //   445: iconst_m1
    //   446: aload 6
    //   448: invokevirtual 457	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   451: iconst_0
    //   452: invokevirtual 195	com/mixpanel/android/java_websocket/WebSocketImpl:flushAndClose	(ILjava/lang/String;Z)V
    //   455: iconst_0
    //   456: ireturn
    //   457: aload_0
    //   458: getfield 105	com/mixpanel/android/java_websocket/WebSocketImpl:draft	Lcom/mixpanel/android/java_websocket/drafts/Draft;
    //   461: ifnonnull +366 -> 827
    //   464: aload_0
    //   465: sipush 1002
    //   468: ldc_w 459
    //   471: invokevirtual 398	com/mixpanel/android/java_websocket/WebSocketImpl:close	(ILjava/lang/String;)V
    //   474: goto +353 -> 827
    //   477: aload_0
    //   478: getfield 105	com/mixpanel/android/java_websocket/WebSocketImpl:draft	Lcom/mixpanel/android/java_websocket/drafts/Draft;
    //   481: aload 4
    //   483: invokevirtual 424	com/mixpanel/android/java_websocket/drafts/Draft:translateHandshake	(Ljava/nio/ByteBuffer;)Lcom/mixpanel/android/java_websocket/handshake/Handshakedata;
    //   486: astore 5
    //   488: aload 5
    //   490: instanceof 426
    //   493: ifne +16 -> 509
    //   496: aload_0
    //   497: sipush 1002
    //   500: ldc_w 428
    //   503: iconst_0
    //   504: invokevirtual 195	com/mixpanel/android/java_websocket/WebSocketImpl:flushAndClose	(ILjava/lang/String;Z)V
    //   507: iconst_0
    //   508: ireturn
    //   509: aload 5
    //   511: checkcast 426	com/mixpanel/android/java_websocket/handshake/ClientHandshake
    //   514: astore 5
    //   516: aload_0
    //   517: getfield 105	com/mixpanel/android/java_websocket/WebSocketImpl:draft	Lcom/mixpanel/android/java_websocket/drafts/Draft;
    //   520: aload 5
    //   522: invokevirtual 432	com/mixpanel/android/java_websocket/drafts/Draft:acceptHandshakeAsServer	(Lcom/mixpanel/android/java_websocket/handshake/ClientHandshake;)Lcom/mixpanel/android/java_websocket/drafts/Draft$HandshakeState;
    //   525: getstatic 381	com/mixpanel/android/java_websocket/drafts/Draft$HandshakeState:MATCHED	Lcom/mixpanel/android/java_websocket/drafts/Draft$HandshakeState;
    //   528: if_acmpne +11 -> 539
    //   531: aload_0
    //   532: aload 5
    //   534: invokespecial 454	com/mixpanel/android/java_websocket/WebSocketImpl:open	(Lcom/mixpanel/android/java_websocket/handshake/Handshakedata;)V
    //   537: iconst_1
    //   538: ireturn
    //   539: aload_0
    //   540: sipush 1002
    //   543: ldc_w 461
    //   546: invokevirtual 398	com/mixpanel/android/java_websocket/WebSocketImpl:close	(ILjava/lang/String;)V
    //   549: iconst_0
    //   550: ireturn
    //   551: aload_0
    //   552: getfield 127	com/mixpanel/android/java_websocket/WebSocketImpl:role	Lcom/mixpanel/android/java_websocket/WebSocket$Role;
    //   555: getstatic 151	com/mixpanel/android/java_websocket/WebSocket$Role:CLIENT	Lcom/mixpanel/android/java_websocket/WebSocket$Role;
    //   558: if_acmpne -330 -> 228
    //   561: aload_0
    //   562: getfield 105	com/mixpanel/android/java_websocket/WebSocketImpl:draft	Lcom/mixpanel/android/java_websocket/drafts/Draft;
    //   565: aload_0
    //   566: getfield 127	com/mixpanel/android/java_websocket/WebSocketImpl:role	Lcom/mixpanel/android/java_websocket/WebSocket$Role;
    //   569: invokevirtual 420	com/mixpanel/android/java_websocket/drafts/Draft:setParseMode	(Lcom/mixpanel/android/java_websocket/WebSocket$Role;)V
    //   572: aload_0
    //   573: getfield 105	com/mixpanel/android/java_websocket/WebSocketImpl:draft	Lcom/mixpanel/android/java_websocket/drafts/Draft;
    //   576: aload 4
    //   578: invokevirtual 424	com/mixpanel/android/java_websocket/drafts/Draft:translateHandshake	(Ljava/nio/ByteBuffer;)Lcom/mixpanel/android/java_websocket/handshake/Handshakedata;
    //   581: astore 5
    //   583: aload 5
    //   585: instanceof 463
    //   588: ifne +16 -> 604
    //   591: aload_0
    //   592: sipush 1002
    //   595: ldc_w 428
    //   598: iconst_0
    //   599: invokevirtual 195	com/mixpanel/android/java_websocket/WebSocketImpl:flushAndClose	(ILjava/lang/String;Z)V
    //   602: iconst_0
    //   603: ireturn
    //   604: aload 5
    //   606: checkcast 463	com/mixpanel/android/java_websocket/handshake/ServerHandshake
    //   609: astore 5
    //   611: aload_0
    //   612: getfield 105	com/mixpanel/android/java_websocket/WebSocketImpl:draft	Lcom/mixpanel/android/java_websocket/drafts/Draft;
    //   615: aload_0
    //   616: getfield 117	com/mixpanel/android/java_websocket/WebSocketImpl:handshakerequest	Lcom/mixpanel/android/java_websocket/handshake/ClientHandshake;
    //   619: aload 5
    //   621: invokevirtual 467	com/mixpanel/android/java_websocket/drafts/Draft:acceptHandshakeAsClient	(Lcom/mixpanel/android/java_websocket/handshake/ClientHandshake;Lcom/mixpanel/android/java_websocket/handshake/ServerHandshake;)Lcom/mixpanel/android/java_websocket/drafts/Draft$HandshakeState;
    //   624: astore 6
    //   626: getstatic 381	com/mixpanel/android/java_websocket/drafts/Draft$HandshakeState:MATCHED	Lcom/mixpanel/android/java_websocket/drafts/Draft$HandshakeState;
    //   629: astore 7
    //   631: aload 6
    //   633: aload 7
    //   635: if_acmpne +73 -> 708
    //   638: aload_0
    //   639: getfield 148	com/mixpanel/android/java_websocket/WebSocketImpl:wsl	Lcom/mixpanel/android/java_websocket/WebSocketListener;
    //   642: aload_0
    //   643: aload_0
    //   644: getfield 117	com/mixpanel/android/java_websocket/WebSocketImpl:handshakerequest	Lcom/mixpanel/android/java_websocket/handshake/ClientHandshake;
    //   647: aload 5
    //   649: invokeinterface 471 4 0
    //   654: aload_0
    //   655: aload 5
    //   657: invokespecial 454	com/mixpanel/android/java_websocket/WebSocketImpl:open	(Lcom/mixpanel/android/java_websocket/handshake/Handshakedata;)V
    //   660: iconst_1
    //   661: ireturn
    //   662: astore 5
    //   664: aload_0
    //   665: aload 5
    //   667: invokevirtual 455	com/mixpanel/android/java_websocket/exceptions/InvalidDataException:getCloseCode	()I
    //   670: aload 5
    //   672: invokevirtual 456	com/mixpanel/android/java_websocket/exceptions/InvalidDataException:getMessage	()Ljava/lang/String;
    //   675: iconst_0
    //   676: invokevirtual 195	com/mixpanel/android/java_websocket/WebSocketImpl:flushAndClose	(ILjava/lang/String;Z)V
    //   679: iconst_0
    //   680: ireturn
    //   681: astore 5
    //   683: aload_0
    //   684: getfield 148	com/mixpanel/android/java_websocket/WebSocketImpl:wsl	Lcom/mixpanel/android/java_websocket/WebSocketListener;
    //   687: aload_0
    //   688: aload 5
    //   690: invokeinterface 224 3 0
    //   695: aload_0
    //   696: iconst_m1
    //   697: aload 5
    //   699: invokevirtual 457	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   702: iconst_0
    //   703: invokevirtual 195	com/mixpanel/android/java_websocket/WebSocketImpl:flushAndClose	(ILjava/lang/String;Z)V
    //   706: iconst_0
    //   707: ireturn
    //   708: aload_0
    //   709: sipush 1002
    //   712: new 255	java/lang/StringBuilder
    //   715: dup
    //   716: invokespecial 256	java/lang/StringBuilder:<init>	()V
    //   719: ldc_w 473
    //   722: invokevirtual 262	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   725: aload_0
    //   726: getfield 105	com/mixpanel/android/java_websocket/WebSocketImpl:draft	Lcom/mixpanel/android/java_websocket/drafts/Draft;
    //   729: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   732: ldc_w 475
    //   735: invokevirtual 262	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   738: invokevirtual 269	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   741: invokevirtual 398	com/mixpanel/android/java_websocket/WebSocketImpl:close	(ILjava/lang/String;)V
    //   744: goto -516 -> 228
    //   747: astore 5
    //   749: aload_0
    //   750: aload 5
    //   752: invokevirtual 302	com/mixpanel/android/java_websocket/WebSocketImpl:close	(Lcom/mixpanel/android/java_websocket/exceptions/InvalidDataException;)V
    //   755: goto -527 -> 228
    //   758: iload_3
    //   759: istore_2
    //   760: getstatic 60	com/mixpanel/android/java_websocket/WebSocketImpl:$assertionsDisabled	Z
    //   763: ifne -552 -> 211
    //   766: iload_3
    //   767: istore_2
    //   768: aload 5
    //   770: invokevirtual 416	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException:getPreferedSize	()I
    //   773: aload 4
    //   775: invokevirtual 401	java/nio/ByteBuffer:remaining	()I
    //   778: if_icmpge -567 -> 211
    //   781: new 191	java/lang/AssertionError
    //   784: dup
    //   785: invokespecial 192	java/lang/AssertionError:<init>	()V
    //   788: athrow
    //   789: aload_0
    //   790: getfield 115	com/mixpanel/android/java_websocket/WebSocketImpl:tmpHandshakeBytes	Ljava/nio/ByteBuffer;
    //   793: aload_0
    //   794: getfield 115	com/mixpanel/android/java_websocket/WebSocketImpl:tmpHandshakeBytes	Ljava/nio/ByteBuffer;
    //   797: invokevirtual 478	java/nio/ByteBuffer:limit	()I
    //   800: invokevirtual 482	java/nio/ByteBuffer:position	(I)Ljava/nio/Buffer;
    //   803: pop
    //   804: aload_0
    //   805: getfield 115	com/mixpanel/android/java_websocket/WebSocketImpl:tmpHandshakeBytes	Ljava/nio/ByteBuffer;
    //   808: aload_0
    //   809: getfield 115	com/mixpanel/android/java_websocket/WebSocketImpl:tmpHandshakeBytes	Ljava/nio/ByteBuffer;
    //   812: invokevirtual 367	java/nio/ByteBuffer:capacity	()I
    //   815: invokevirtual 484	java/nio/ByteBuffer:limit	(I)Ljava/nio/Buffer;
    //   818: pop
    //   819: goto -591 -> 228
    //   822: astore 6
    //   824: goto -566 -> 258
    //   827: iconst_0
    //   828: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	829	0	this	WebSocketImpl
    //   0	829	1	paramByteBuffer	ByteBuffer
    //   210	558	2	i	int
    //   197	570	3	j	int
    //   11	763	4	localByteBuffer	ByteBuffer
    //   32	8	5	localHandshakeState1	Draft.HandshakeState
    //   158	1	5	localInvalidDataException1	InvalidDataException
    //   174	19	5	localIncompleteHandshakeException	IncompleteHandshakeException
    //   256	400	5	localObject1	Object
    //   662	9	5	localInvalidDataException2	InvalidDataException
    //   681	17	5	localRuntimeException1	RuntimeException
    //   747	22	5	localInvalidHandshakeException1	InvalidHandshakeException
    //   37	362	6	localObject2	Object
    //   411	9	6	localInvalidDataException3	InvalidDataException
    //   430	17	6	localRuntimeException2	RuntimeException
    //   624	8	6	localHandshakeState2	Draft.HandshakeState
    //   822	1	6	localInvalidHandshakeException2	InvalidHandshakeException
    //   305	329	7	localObject3	Object
    //   373	10	8	localServerHandshakeBuilder	com.mixpanel.android.java_websocket.handshake.ServerHandshakeBuilder
    // Exception table:
    //   from	to	target	type
    //   46	75	158	com/mixpanel/android/java_websocket/exceptions/InvalidDataException
    //   19	39	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   46	75	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   160	171	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   230	258	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   258	283	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   283	326	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   328	359	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   359	375	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   375	409	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   413	428	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   432	455	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   457	474	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   477	507	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   509	537	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   539	549	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   551	602	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   604	631	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   638	654	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   654	660	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   664	679	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   683	706	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   708	744	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   749	755	174	com/mixpanel/android/java_websocket/exceptions/IncompleteHandshakeException
    //   359	375	411	com/mixpanel/android/java_websocket/exceptions/InvalidDataException
    //   359	375	430	java/lang/RuntimeException
    //   638	654	662	com/mixpanel/android/java_websocket/exceptions/InvalidDataException
    //   638	654	681	java/lang/RuntimeException
    //   230	258	747	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   258	283	747	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   457	474	747	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   477	507	747	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   509	537	747	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   539	549	747	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   551	602	747	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   604	631	747	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   638	654	747	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   654	660	747	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   664	679	747	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   683	706	747	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   708	744	747	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   283	326	822	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   328	359	822	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   359	375	822	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   375	409	822	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   413	428	822	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
    //   432	455	822	com/mixpanel/android/java_websocket/exceptions/InvalidHandshakeException
  }
  
  private Draft.HandshakeState isFlashEdgeCase(ByteBuffer paramByteBuffer)
    throws IncompleteHandshakeException
  {
    paramByteBuffer.mark();
    if (paramByteBuffer.limit() > Draft.FLASH_POLICY_REQUEST.length) {
      return Draft.HandshakeState.NOT_MATCHED;
    }
    if (paramByteBuffer.limit() < Draft.FLASH_POLICY_REQUEST.length) {
      throw new IncompleteHandshakeException(Draft.FLASH_POLICY_REQUEST.length);
    }
    int i = 0;
    while (paramByteBuffer.hasRemaining())
    {
      if (Draft.FLASH_POLICY_REQUEST[i] != paramByteBuffer.get())
      {
        paramByteBuffer.reset();
        return Draft.HandshakeState.NOT_MATCHED;
      }
      i += 1;
    }
    return Draft.HandshakeState.MATCHED;
  }
  
  private void open(Handshakedata paramHandshakedata)
  {
    if (DEBUG) {
      System.out.println("open using draft: " + this.draft.getClass().getSimpleName());
    }
    this.readystate = WebSocket.READYSTATE.OPEN;
    try
    {
      this.wsl.onWebsocketOpen(this, paramHandshakedata);
      return;
    }
    catch (RuntimeException paramHandshakedata)
    {
      this.wsl.onWebsocketError(this, paramHandshakedata);
    }
  }
  
  private void send(Collection<Framedata> paramCollection)
  {
    if (!isOpen()) {
      throw new WebsocketNotConnectedException();
    }
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      sendFrame((Framedata)paramCollection.next());
    }
  }
  
  private void write(ByteBuffer paramByteBuffer)
  {
    PrintStream localPrintStream;
    StringBuilder localStringBuilder;
    if (DEBUG)
    {
      localPrintStream = System.out;
      localStringBuilder = new StringBuilder().append("write(").append(paramByteBuffer.remaining()).append("): {");
      if (paramByteBuffer.remaining() <= 1000) {
        break label93;
      }
    }
    label93:
    for (String str = "too big to display";; str = new String(paramByteBuffer.array()))
    {
      localPrintStream.println(str + "}");
      this.outQueue.add(paramByteBuffer);
      this.wsl.onWriteDemand(this);
      return;
    }
  }
  
  private void write(List<ByteBuffer> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      write((ByteBuffer)paramList.next());
    }
  }
  
  public void close()
  {
    close(1000);
  }
  
  public void close(int paramInt)
  {
    close(paramInt, "", false);
  }
  
  public void close(int paramInt, String paramString)
  {
    close(paramInt, paramString, false);
  }
  
  public void close(InvalidDataException paramInvalidDataException)
  {
    close(paramInvalidDataException.getCloseCode(), paramInvalidDataException.getMessage(), false);
  }
  
  public void closeConnection()
  {
    if (this.closedremotely == null) {
      throw new IllegalStateException("this method must be used in conjuction with flushAndClose");
    }
    closeConnection(this.closecode.intValue(), this.closemessage, this.closedremotely.booleanValue());
  }
  
  public void closeConnection(int paramInt, String paramString)
  {
    closeConnection(paramInt, paramString, false);
  }
  
  /* Error */
  protected void closeConnection(int paramInt, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 103	com/mixpanel/android/java_websocket/WebSocketImpl:readystate	Lcom/mixpanel/android/java_websocket/WebSocket$READYSTATE;
    //   6: astore 4
    //   8: getstatic 186	com/mixpanel/android/java_websocket/WebSocket$READYSTATE:CLOSED	Lcom/mixpanel/android/java_websocket/WebSocket$READYSTATE;
    //   11: astore 5
    //   13: aload 4
    //   15: aload 5
    //   17: if_acmpne +6 -> 23
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: aload_0
    //   24: getfield 574	com/mixpanel/android/java_websocket/WebSocketImpl:key	Ljava/nio/channels/SelectionKey;
    //   27: ifnull +10 -> 37
    //   30: aload_0
    //   31: getfield 574	com/mixpanel/android/java_websocket/WebSocketImpl:key	Ljava/nio/channels/SelectionKey;
    //   34: invokevirtual 579	java/nio/channels/SelectionKey:cancel	()V
    //   37: aload_0
    //   38: getfield 581	com/mixpanel/android/java_websocket/WebSocketImpl:channel	Ljava/nio/channels/ByteChannel;
    //   41: astore 4
    //   43: aload 4
    //   45: ifnull +12 -> 57
    //   48: aload_0
    //   49: getfield 581	com/mixpanel/android/java_websocket/WebSocketImpl:channel	Ljava/nio/channels/ByteChannel;
    //   52: invokeinterface 585 1 0
    //   57: aload_0
    //   58: getfield 148	com/mixpanel/android/java_websocket/WebSocketImpl:wsl	Lcom/mixpanel/android/java_websocket/WebSocketListener;
    //   61: aload_0
    //   62: iload_1
    //   63: aload_2
    //   64: iload_3
    //   65: invokeinterface 589 5 0
    //   70: aload_0
    //   71: getfield 105	com/mixpanel/android/java_websocket/WebSocketImpl:draft	Lcom/mixpanel/android/java_websocket/drafts/Draft;
    //   74: ifnull +10 -> 84
    //   77: aload_0
    //   78: getfield 105	com/mixpanel/android/java_websocket/WebSocketImpl:draft	Lcom/mixpanel/android/java_websocket/drafts/Draft;
    //   81: invokevirtual 591	com/mixpanel/android/java_websocket/drafts/Draft:reset	()V
    //   84: aload_0
    //   85: aconst_null
    //   86: putfield 117	com/mixpanel/android/java_websocket/WebSocketImpl:handshakerequest	Lcom/mixpanel/android/java_websocket/handshake/ClientHandshake;
    //   89: aload_0
    //   90: getstatic 186	com/mixpanel/android/java_websocket/WebSocket$READYSTATE:CLOSED	Lcom/mixpanel/android/java_websocket/WebSocket$READYSTATE;
    //   93: putfield 103	com/mixpanel/android/java_websocket/WebSocketImpl:readystate	Lcom/mixpanel/android/java_websocket/WebSocket$READYSTATE;
    //   96: aload_0
    //   97: getfield 144	com/mixpanel/android/java_websocket/WebSocketImpl:outQueue	Ljava/util/concurrent/BlockingQueue;
    //   100: invokeinterface 594 1 0
    //   105: goto -85 -> 20
    //   108: astore_2
    //   109: aload_0
    //   110: monitorexit
    //   111: aload_2
    //   112: athrow
    //   113: astore 4
    //   115: aload_0
    //   116: getfield 148	com/mixpanel/android/java_websocket/WebSocketImpl:wsl	Lcom/mixpanel/android/java_websocket/WebSocketListener;
    //   119: aload_0
    //   120: aload 4
    //   122: invokeinterface 224 3 0
    //   127: goto -70 -> 57
    //   130: astore_2
    //   131: aload_0
    //   132: getfield 148	com/mixpanel/android/java_websocket/WebSocketImpl:wsl	Lcom/mixpanel/android/java_websocket/WebSocketListener;
    //   135: aload_0
    //   136: aload_2
    //   137: invokeinterface 224 3 0
    //   142: goto -72 -> 70
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	145	0	this	WebSocketImpl
    //   0	145	1	paramInt	int
    //   0	145	2	paramString	String
    //   0	145	3	paramBoolean	boolean
    //   6	38	4	localObject	Object
    //   113	8	4	localIOException	java.io.IOException
    //   11	5	5	localREADYSTATE	WebSocket.READYSTATE
    // Exception table:
    //   from	to	target	type
    //   2	13	108	finally
    //   23	37	108	finally
    //   37	43	108	finally
    //   48	57	108	finally
    //   57	70	108	finally
    //   70	84	108	finally
    //   84	105	108	finally
    //   115	127	108	finally
    //   131	142	108	finally
    //   48	57	113	java/io/IOException
    //   57	70	130	java/lang/RuntimeException
  }
  
  protected void closeConnection(int paramInt, boolean paramBoolean)
  {
    closeConnection(paramInt, "", paramBoolean);
  }
  
  public void decode(ByteBuffer paramByteBuffer)
  {
    assert (paramByteBuffer.hasRemaining());
    String str;
    if (DEBUG)
    {
      PrintStream localPrintStream = System.out;
      StringBuilder localStringBuilder = new StringBuilder().append("process(").append(paramByteBuffer.remaining()).append("): {");
      if (paramByteBuffer.remaining() > 1000)
      {
        str = "too big to display";
        localPrintStream.println(str + "}");
      }
    }
    else
    {
      if (this.readystate == WebSocket.READYSTATE.NOT_YET_CONNECTED) {
        break label165;
      }
      decodeFrames(paramByteBuffer);
    }
    for (;;)
    {
      if (($assertionsDisabled) || (isClosing()) || (isFlushAndClose()) || (!paramByteBuffer.hasRemaining())) {
        return;
      }
      throw new AssertionError();
      str = new String(paramByteBuffer.array(), paramByteBuffer.position(), paramByteBuffer.remaining());
      break;
      label165:
      if (decodeHandshake(paramByteBuffer))
      {
        assert ((this.tmpHandshakeBytes.hasRemaining() != paramByteBuffer.hasRemaining()) || (!paramByteBuffer.hasRemaining()));
        if (paramByteBuffer.hasRemaining()) {
          decodeFrames(paramByteBuffer);
        } else if (this.tmpHandshakeBytes.hasRemaining()) {
          decodeFrames(this.tmpHandshakeBytes);
        }
      }
    }
  }
  
  public void eot()
  {
    if (getReadyState() == WebSocket.READYSTATE.NOT_YET_CONNECTED)
    {
      closeConnection(-1, true);
      return;
    }
    if (this.flushandclosestate)
    {
      closeConnection(this.closecode.intValue(), this.closemessage, this.closedremotely.booleanValue());
      return;
    }
    if (this.draft.getCloseHandshakeType() == Draft.CloseHandshakeType.NONE)
    {
      closeConnection(1000, true);
      return;
    }
    if (this.draft.getCloseHandshakeType() == Draft.CloseHandshakeType.ONEWAY)
    {
      if (this.role == WebSocket.Role.SERVER)
      {
        closeConnection(1006, true);
        return;
      }
      closeConnection(1000, true);
      return;
    }
    closeConnection(1006, true);
  }
  
  /* Error */
  protected void flushAndClose(int paramInt, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 96	com/mixpanel/android/java_websocket/WebSocketImpl:flushandclosestate	Z
    //   6: istore 4
    //   8: iload 4
    //   10: ifeq +6 -> 16
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: aload_0
    //   17: iload_1
    //   18: invokestatic 627	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   21: putfield 121	com/mixpanel/android/java_websocket/WebSocketImpl:closecode	Ljava/lang/Integer;
    //   24: aload_0
    //   25: aload_2
    //   26: putfield 119	com/mixpanel/android/java_websocket/WebSocketImpl:closemessage	Ljava/lang/String;
    //   29: aload_0
    //   30: iload_3
    //   31: invokestatic 630	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   34: putfield 123	com/mixpanel/android/java_websocket/WebSocketImpl:closedremotely	Ljava/lang/Boolean;
    //   37: aload_0
    //   38: iconst_1
    //   39: putfield 96	com/mixpanel/android/java_websocket/WebSocketImpl:flushandclosestate	Z
    //   42: aload_0
    //   43: getfield 148	com/mixpanel/android/java_websocket/WebSocketImpl:wsl	Lcom/mixpanel/android/java_websocket/WebSocketListener;
    //   46: aload_0
    //   47: invokeinterface 543 2 0
    //   52: aload_0
    //   53: getfield 148	com/mixpanel/android/java_websocket/WebSocketImpl:wsl	Lcom/mixpanel/android/java_websocket/WebSocketListener;
    //   56: aload_0
    //   57: iload_1
    //   58: aload_2
    //   59: iload_3
    //   60: invokeinterface 633 5 0
    //   65: aload_0
    //   66: getfield 105	com/mixpanel/android/java_websocket/WebSocketImpl:draft	Lcom/mixpanel/android/java_websocket/drafts/Draft;
    //   69: ifnull +10 -> 79
    //   72: aload_0
    //   73: getfield 105	com/mixpanel/android/java_websocket/WebSocketImpl:draft	Lcom/mixpanel/android/java_websocket/drafts/Draft;
    //   76: invokevirtual 591	com/mixpanel/android/java_websocket/drafts/Draft:reset	()V
    //   79: aload_0
    //   80: aconst_null
    //   81: putfield 117	com/mixpanel/android/java_websocket/WebSocketImpl:handshakerequest	Lcom/mixpanel/android/java_websocket/handshake/ClientHandshake;
    //   84: goto -71 -> 13
    //   87: astore_2
    //   88: aload_0
    //   89: monitorexit
    //   90: aload_2
    //   91: athrow
    //   92: astore_2
    //   93: aload_0
    //   94: getfield 148	com/mixpanel/android/java_websocket/WebSocketImpl:wsl	Lcom/mixpanel/android/java_websocket/WebSocketListener;
    //   97: aload_0
    //   98: aload_2
    //   99: invokeinterface 224 3 0
    //   104: goto -39 -> 65
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	107	0	this	WebSocketImpl
    //   0	107	1	paramInt	int
    //   0	107	2	paramString	String
    //   0	107	3	paramBoolean	boolean
    //   6	3	4	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	8	87	finally
    //   16	52	87	finally
    //   52	65	87	finally
    //   65	79	87	finally
    //   79	84	87	finally
    //   93	104	87	finally
    //   52	65	92	java/lang/RuntimeException
  }
  
  public Draft getDraft()
  {
    return this.draft;
  }
  
  public InetSocketAddress getLocalSocketAddress()
  {
    return this.wsl.getLocalSocketAddress(this);
  }
  
  public WebSocket.READYSTATE getReadyState()
  {
    return this.readystate;
  }
  
  public InetSocketAddress getRemoteSocketAddress()
  {
    return this.wsl.getRemoteSocketAddress(this);
  }
  
  public String getResourceDescriptor()
  {
    return this.resourceDescriptor;
  }
  
  public boolean hasBufferedData()
  {
    return !this.outQueue.isEmpty();
  }
  
  public int hashCode()
  {
    return super.hashCode();
  }
  
  public boolean isClosed()
  {
    return this.readystate == WebSocket.READYSTATE.CLOSED;
  }
  
  public boolean isClosing()
  {
    return this.readystate == WebSocket.READYSTATE.CLOSING;
  }
  
  public boolean isConnecting()
  {
    if (($assertionsDisabled) || (!this.flushandclosestate) || (this.readystate == WebSocket.READYSTATE.CONNECTING))
    {
      if (this.readystate == WebSocket.READYSTATE.CONNECTING) {
        return true;
      }
    }
    else {
      throw new AssertionError();
    }
    return false;
  }
  
  public boolean isFlushAndClose()
  {
    return this.flushandclosestate;
  }
  
  public boolean isOpen()
  {
    if (($assertionsDisabled) || (this.readystate != WebSocket.READYSTATE.OPEN) || (!this.flushandclosestate))
    {
      if (this.readystate == WebSocket.READYSTATE.OPEN) {
        return true;
      }
    }
    else {
      throw new AssertionError();
    }
    return false;
  }
  
  public void send(String paramString)
    throws WebsocketNotConnectedException
  {
    if (paramString == null) {
      throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
    }
    Draft localDraft = this.draft;
    if (this.role == WebSocket.Role.CLIENT) {}
    for (boolean bool = true;; bool = false)
    {
      send(localDraft.createFrames(paramString, bool));
      return;
    }
  }
  
  public void send(ByteBuffer paramByteBuffer)
    throws IllegalArgumentException, WebsocketNotConnectedException
  {
    if (paramByteBuffer == null) {
      throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
    }
    Draft localDraft = this.draft;
    if (this.role == WebSocket.Role.CLIENT) {}
    for (boolean bool = true;; bool = false)
    {
      send(localDraft.createFrames(paramByteBuffer, bool));
      return;
    }
  }
  
  public void send(byte[] paramArrayOfByte)
    throws IllegalArgumentException, WebsocketNotConnectedException
  {
    send(ByteBuffer.wrap(paramArrayOfByte));
  }
  
  public void sendFragmentedFrame(Framedata.Opcode paramOpcode, ByteBuffer paramByteBuffer, boolean paramBoolean)
  {
    send(this.draft.continuousFrame(paramOpcode, paramByteBuffer, paramBoolean));
  }
  
  public void sendFrame(Framedata paramFramedata)
  {
    if (DEBUG) {
      System.out.println("send frame: " + paramFramedata);
    }
    write(this.draft.createBinaryFrame(paramFramedata));
  }
  
  public void startHandshake(ClientHandshakeBuilder paramClientHandshakeBuilder)
    throws InvalidHandshakeException
  {
    assert (this.readystate != WebSocket.READYSTATE.CONNECTING) : "shall only be called once";
    this.handshakerequest = this.draft.postProcessHandshakeRequestAsClient(paramClientHandshakeBuilder);
    this.resourceDescriptor = paramClientHandshakeBuilder.getResourceDescriptor();
    assert (this.resourceDescriptor != null);
    try
    {
      this.wsl.onWebsocketHandshakeSentAsClient(this, this.handshakerequest);
      write(this.draft.createHandshake(this.handshakerequest, this.role));
      return;
    }
    catch (InvalidDataException paramClientHandshakeBuilder)
    {
      throw new InvalidHandshakeException("Handshake data rejected by client.");
    }
    catch (RuntimeException paramClientHandshakeBuilder)
    {
      this.wsl.onWebsocketError(this, paramClientHandshakeBuilder);
      throw new InvalidHandshakeException("rejected because of" + paramClientHandshakeBuilder);
    }
  }
  
  public String toString()
  {
    return super.toString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/mixpanel/android/java_websocket/WebSocketImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */