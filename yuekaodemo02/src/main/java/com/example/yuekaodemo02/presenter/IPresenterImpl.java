package com.example.yuekaodemo02.presenter;


import com.example.yuekaodemo02.bean.Data;
import com.example.yuekaodemo02.callback.MyCallBack;
import com.example.yuekaodemo02.ivew.IVew;
import com.example.yuekaodemo02.model.ModelImpl;

public class IPresenterImpl implements IPresenter{
    private ModelImpl model;
    private IVew iVew;

    public IPresenterImpl(IVew iVew) {
        this.iVew = iVew;
        model=new ModelImpl();
    }

    @Override
    public void getData(String uri) {
      model.getData(uri, new MyCallBack() {
          @Override
          public void onSuccess(Data data) {
              iVew.showData(data);
          }

          @Override
          public void onFail(Exception e) {

          }
      });
    }
}
