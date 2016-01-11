package com.gojek.gobox.contactPersonDetailForm.presenter;

import com.gojek.gobox.contactPersonDetailForm.interactor.ContactPersonDetailFormInteractor;

public class ContactPersonDetailFormPresenterImpl
  implements ContactPersonDetailFormPresenter
{
  private ContactPersonDetailFormInteractor mContactPersonDetailPresenter;
  
  public ContactPersonDetailFormPresenterImpl(ContactPersonDetailFormInteractor paramContactPersonDetailFormInteractor)
  {
    this.mContactPersonDetailPresenter = paramContactPersonDetailFormInteractor;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/contactPersonDetailForm/presenter/ContactPersonDetailFormPresenterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */