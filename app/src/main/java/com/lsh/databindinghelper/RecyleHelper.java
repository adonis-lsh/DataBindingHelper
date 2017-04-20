package com.lsh.databindinghelper;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lsh.databinghelper.command.ReplyCommand;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;

/**
 * Created by "小灰灰"
 * on 20/4/2017 11:00
 * 邮箱：www.adonis_lsh.com
 */

public class RecyleHelper {

    public  ObservableList<TestBean> items = new ObservableArrayList<>();
    public  ObservableList<String> stringItems = new ObservableArrayList<>();

    public final MergeObservableList<Object> headerFooterItems = new MergeObservableList<>()
            .insertList(items);



    public RecyleHelper() {
        for (int i = 0; i < 20; i++) {
            items.add(new TestBean("李胜辉"));
            items.add(new TestBean("李宁"));
            stringItems.add("测试一下");
        }
    }


    public BindingRecyclerViewAdapter adapter = new BindingRecyclerViewAdapter();

//    public final OnItemBindClass<Object> multipleItems = new OnItemBindClass<>()
//            .map(String.class,BR.item,R.layout.item_header_footer)
//            .map(TestBean.class, com.lsh.databindinghelper.BR.item, R.layout.item);

    public OnItemBind<Object> multipleItems = new OnItemBind<Object>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, Object item) {
            if (TestBean.class.equals(item.getClass())) {
                TestBean testBean = (TestBean) item;
                if (testBean.name.equals("李胜辉")) {
                    itemBinding.set(BR.item, R.layout.item_header_footer);
                } else {
                    itemBinding.set(BR.item, R.layout.item);
                }
            }
        }
    };


    public BindingRecyclerViewAdapter.ViewHolderFactory viewHolder = new BindingRecyclerViewAdapter.ViewHolderFactory() {

        @Override
        public RecyclerView.ViewHolder createViewHolder(ViewDataBinding binding) {
            return new MyViewHolder(binding.getRoot());
        }
    };

    private static class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public final ReplyCommand<Integer> onLoadMoreCommand = new ReplyCommand<>((p) -> {
        for (int i = 0; i < 20; i++) {
            items.add(new TestBean("李胜辉"));
            items.add(new TestBean("李宁"));
        }
    });
}
