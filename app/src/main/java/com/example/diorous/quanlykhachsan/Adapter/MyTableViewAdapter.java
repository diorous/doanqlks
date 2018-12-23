package com.example.diorous.quanlykhachsan.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.example.diorous.quanlykhachsan.Model.Cell;
import com.example.diorous.quanlykhachsan.Model.ColumnHeader;
import com.example.diorous.quanlykhachsan.Model.RowHeader;
import com.example.diorous.quanlykhachsan.Model.TableViewModel;

public class MyTableViewAdapter extends AbstractTableAdapter<ColumnHeader, RowHeader, Cell> {


    public MyTableViewAdapter(Context context, TableViewModel mTableViewModel) {
        super(context);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        return 0;
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object cellItemModel, int columnPosition, int rowPosition) {

    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object columnHeaderItemModel, int columnPosition) {

    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object rowHeaderItemModel, int rowPosition) {

    }

    @Override
    public View onCreateCornerView() {
        return null;
    }
}
