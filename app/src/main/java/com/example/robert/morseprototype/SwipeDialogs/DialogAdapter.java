package com.example.robert.morseprototype.SwipeDialogs;


import android.app.Activity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import com.example.robert.morseprototype.R;

        import java.util.List;


@SuppressWarnings("unchecked")
class DialogAdapter extends ArrayAdapter<MorseSymbols> {
    private final LayoutInflater mInflater;

    public DialogAdapter(Activity context, List list) {
        super(context, R.layout.dialog_item, list);

        mInflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = mInflater.inflate(R.layout.dialog_item, parent, false);
        } else {
            view = convertView;
        }

        MorseSymbols m1 = getItem(position);

        TextView txtTitle = (TextView) view.findViewById(R.id.item);
        TextView extraTxt = (TextView) view.findViewById(R.id.textView1);

        txtTitle.setText(m1.getLetter());
        extraTxt.setText(m1.getMorseSymbol());
        return view;
    }
}