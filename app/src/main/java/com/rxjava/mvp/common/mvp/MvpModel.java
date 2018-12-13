package com.rxjava.mvp.common.mvp;

/**
 *    desc   : MVP 模型基类
 * @param <L>
 */
public abstract class MvpModel<L> {

    private L mListener;

    public void setListener(L l) {
        mListener = l;
    }

    public L getListener() {
        return mListener;
    }

}
