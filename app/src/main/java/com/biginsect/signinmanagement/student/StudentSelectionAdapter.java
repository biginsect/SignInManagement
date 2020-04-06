package com.biginsect.signinmanagement.student;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.biginsect.signinmanagement.R;
import com.biginsect.signinmanagement.bean.StudentSelection;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author biginsect
 * @date 2020/4/4
 */
public class StudentSelectionAdapter extends BaseQuickAdapter<StudentSelection, StudentSelectionAdapter.ViewHolder> {

    public StudentSelectionAdapter() {
        super(R.layout.recycler_item_student_selection);
    }

    @Override
    protected void convert(ViewHolder holder, StudentSelection selection) {
        holder.mIvSelection.setImageResource(selection.getImageId());
        holder.mTvSelection.setText(selection.getSelectionText());
    }

    static class ViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_student_selection)
        ImageView mIvSelection;
        @BindView(R.id.tv_student_selection)
        TextView mTvSelection;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
