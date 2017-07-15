package com.dongnao.ui.master.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshuai on 2017/7/15.
 */

public class FlowLayoutView extends ViewGroup {

    //保存每行view的列表
    private List<List<View>> mViewLinesList=new ArrayList<List<View>>();
    //保存行高的列表
    private List<Integer> mLineHeights=new ArrayList<Integer>();

    public FlowLayoutView(Context context, AttributeSet attrs) { super(context, attrs); }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) { return new MarginLayoutParams(getContext(),attrs); }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取父容器为FlowLayoutView设置的测量模式大小
        int iWidthMode=MeasureSpec.getMode(widthMeasureSpec);
        int iHeightMode=MeasureSpec.getMode(heightMeasureSpec);
        int iWidthSpecSize=MeasureSpec.getSize(widthMeasureSpec);
        int iHeightSpecSize=MeasureSpec.getSize(heightMeasureSpec);

        int measuredWidth=0;
        int measuredHeight=0;
        int iCurLineW=0;
        int iCurLineH=0;
        
        if (iWidthMode == MeasureSpec.EXACTLY && iHeightMode == MeasureSpec.EXACTLY){
            measuredWidth=iWidthSpecSize;
            measuredHeight=iHeightSpecSize;
        }else {
            int iChildWidth;
            int iChildHeight;
            int childCount=getChildCount();
            List<View> viewList=new ArrayList<View>();

            for (int i = 0; i < childCount; i++) {

                View childView=getChildAt(i);
                measureChild(childView,widthMeasureSpec, heightMeasureSpec);

                MarginLayoutParams layoutParams= (MarginLayoutParams) childView.getLayoutParams();
                iChildWidth = childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                iChildHeight= childView.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

                if (iCurLineW + iChildWidth > iWidthSpecSize) {
                    //记录当前行的信息
                    //1、记录当前行的最大宽度，高度累加
                    measuredWidth=Math.max(measuredWidth,iCurLineW);
                    measuredHeight += iCurLineH;

                    //将当前行的viewList添加至总的mViewLinesList
                    mViewLinesList.add(viewList);
                    mLineHeights.add(iCurLineH);


                    iCurLineW =iChildWidth;
                    iCurLineH=iChildHeight;

                    viewList=new ArrayList<View>();
                    viewList.add(childView);

                }else {
                    iCurLineW += iChildWidth;
                    iCurLineH=Math.max(iCurLineH,iChildHeight);

                    viewList.add(childView);
                }
                
                if (i==childCount-1){
                    measuredWidth=Math.max(measuredWidth,iCurLineW);
                    measuredHeight += iCurLineH ;
                    mViewLinesList.add(viewList);
                    mLineHeights.add(iCurLineH);
                }

            }
        }

        //通过子View的规格大小来确定自己的大小
        setMeasuredDimension(measuredWidth,measuredHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int left,top,right,bottom;
        int curTop=0,curLeft=0;  //顶点
        int lineCount=mViewLinesList.size();

        for (int i = 0; i < lineCount; i++) {

            List<View> viewList=mViewLinesList.get(i);
            int lineViewSize=viewList.size();

            for (int j = 0; j < lineViewSize; j++) {
                View childView =viewList.get(j);

                MarginLayoutParams layoutParams= (MarginLayoutParams) childView.getLayoutParams();

                left = curLeft + layoutParams.leftMargin;

                top =curTop + layoutParams.topMargin;

                right = left + childView.getMeasuredWidth();

                bottom = top + childView.getMeasuredHeight();

                childView.layout(left,top,right,bottom);

                curLeft += childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            }
            curLeft = 0;
            curTop += mLineHeights.get(i);
        }

        mViewLinesList.clear();
        mLineHeights.clear();
        
    }


    public interface OnItemClickListener{
        void onItemListener(View view,int position);
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener){

        int childCount=getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            final int finalI = i;
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemListener(v, finalI+1);
                }
            });
        }
    }

}
