package pt.ips.pam.newpetfriend;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AnimalListAdapter extends ArrayAdapter<Animal> {
    private final List<Animal> list;
    private final Activity context;

    public AnimalListAdapter(Activity context, List<Animal> list) {
        super(context, R.layout.item_animal, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder { protected TextView name; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = context.getLayoutInflater().inflate(R.layout.item_animal, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = view.findViewById(R.id.username);
            view.setTag(viewHolder);

        } else { view = convertView; }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.name.setText(list.get(position).getNomeAnimal());

        return view;
    }
}
