package com.example.lesson_6_fedin;

import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AdapterRecyclerViewCounter
        extends RecyclerView.Adapter<AdapterRecyclerViewCounter.HolderCounter> {

    public interface AdapterClickListr {
        void infoClick(int position);

        void optionClick(int position);
        // void buttonEditTextClick(HolderCounter holder, int position);
    }

    private ArrayList<DataCounter> dataCounters;

    private AdapterClickListr adapterClickListener;

    public static class HolderCounter extends RecyclerView.ViewHolder {
        View layout;
        TextView title;
        ImageView imageView;
        TextView idUser;
        View layoutFirstEditText;
        View layoutTwoEditText;
        View layoutThreeEditText;
        TextView textViewLayoutFirstEditText;
        TextView textViewLayoutTwoEditText;
        TextView textViewLayoutThreeEditText;
        EditText editTextLayoutFirstEditText;
        EditText editTextLayoutTwoEditText;
        EditText editTextLayoutThreeEditText;
        TextView description;
        View information;
        View options;
        View button;

        public HolderCounter(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.imageView);
            idUser = itemView.findViewById(R.id.textView);
            layoutFirstEditText = itemView.findViewById(R.id.firstEditText);
            layoutTwoEditText = itemView.findViewById(R.id.twoEditText);
            layoutThreeEditText = itemView.findViewById(R.id.threeEditText);
            textViewLayoutFirstEditText = layoutFirstEditText.findViewById(R.id.textView);
            textViewLayoutTwoEditText = layoutTwoEditText.findViewById(R.id.textView);
            textViewLayoutThreeEditText = layoutThreeEditText.findViewById(R.id.textView);
            editTextLayoutFirstEditText = layoutFirstEditText.findViewById(R.id.editText);
            editTextLayoutTwoEditText = layoutTwoEditText.findViewById(R.id.editText);
            editTextLayoutThreeEditText = layoutThreeEditText.findViewById(R.id.editText);
            description = itemView.findViewById(R.id.description);
            information = itemView.findViewById(R.id.info);
            options = itemView.findViewById(R.id.more);
            button = itemView.findViewById(R.id.button);
        }

        public void bindHolder(final DataCounter item, final int position,
                               final AdapterClickListr adapterClickListr, final AdapterRecyclerViewCounter adapter) {
            title.setText(item.getTitle());
            imageView.setImageDrawable(layout.getContext().getResources().getDrawable(item.getIcon()));
            idUser.setText(Integer.toString(item.getIdUser()));
            visibleView(item.getTypeCounter());
            description(item);

            information.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapterClickListr.infoClick(position);
                }
            });

            options.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapterClickListr.optionClick(position);
                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String counter1 = editTextLayoutFirstEditText.getText().toString();
                    String counter2 = editTextLayoutTwoEditText.getText().toString();
                    String counter3 = editTextLayoutThreeEditText.getText().toString();
                    switch (item.getTypeCounter()) {
                        case 1:
                            if (!counter1.isEmpty()) {
                                item.setOneCounter(Integer.parseInt(counter1));
                                item.setLastIndication(new Date());
                                adapter.notifyItemChanged(position);
                            } else
                                Toast.makeText(layout.getContext(),
                                        "Не введено показание счетчика", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            if (!counter1.isEmpty() & !counter2.isEmpty()) {
                                item.setOneCounter(Integer.parseInt(counter1));
                                item.setTwoCounter(Integer.parseInt(counter2));
                                item.setLastIndication(new Date());
                                adapter.notifyItemChanged(position);
                            } else
                                Toast.makeText(layout.getContext(),
                                        "Не введено показание счетчика", Toast.LENGTH_SHORT).show();
                            break;

                        case 3:
                            if (!counter1.isEmpty() & !counter2.isEmpty() & !counter3.isEmpty()) {
                                item.setOneCounter(Integer.parseInt(counter1));
                                item.setTwoCounter(Integer.parseInt(counter2));
                                item.setThreeCounter(Integer.parseInt(counter3));
                                item.setLastIndication(new Date());
                                adapter.notifyItemChanged(position);
                            } else
                                Toast.makeText(layout.getContext(),
                                        "Не введено показание счетчика", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }


        private void visibleView(int typeCounter) {
            switch (typeCounter) {
                case DataCounter.FIRST_TYPE:
                    layoutFirstEditText.setVisibility(View.VISIBLE);
                    layoutTwoEditText.setVisibility(View.GONE);
                    layoutThreeEditText.setVisibility(View.GONE);

                    textViewLayoutFirstEditText.setText(layout.getContext().getText(R.string.new_meter_reading));
                    break;
                case DataCounter.TWO_TYPE:
                    layoutFirstEditText.setVisibility(View.VISIBLE);
                    layoutTwoEditText.setVisibility(View.VISIBLE);
                    layoutThreeEditText.setVisibility(View.GONE);

                    textViewLayoutFirstEditText.setText(layout.getContext().getText(R.string.day));
                    textViewLayoutTwoEditText.setText(layout.getContext().getText(R.string.night));
                    break;
                case DataCounter.THREE_TYPE:
                    layoutFirstEditText.setVisibility(View.VISIBLE);
                    layoutTwoEditText.setVisibility(View.VISIBLE);
                    layoutThreeEditText.setVisibility(View.VISIBLE);


                    textViewLayoutFirstEditText.setText(layout.getContext().getText(R.string.day));
                    textViewLayoutTwoEditText.setText(layout.getContext().getText(R.string.night));
                    textViewLayoutThreeEditText.setText(layout.getContext().getText(R.string.peak));
                    break;
            }
        }

        private void description(DataCounter item) {

            //Date range = new Date(item.getNextIndication().getTime() - (long) 1000 * 3600 * 24 * 30);
            Calendar range = new GregorianCalendar();
            range.setTime(item.getNextIndication());
            range.add(Calendar.MONTH, -1);
            Calendar lastIndication = new GregorianCalendar();
            lastIndication.setTime(item.getLastIndication());

            if (lastIndication.after(range)) {
                descriptionOk(item.getLastIndication(), item.getNextIndication());
            } else {
                descriptionBad(item.getNextIndication());
            }
        }

        private void descriptionOk(Date lastDate, Date nextDate) {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            String str = layout.getContext().getResources().getString(R.string.discription_ok,
                    "<b>" + format.format(lastDate) + "</b>", "<b>" + format.format(nextDate) + "</b>");
            description.setTextColor(layout.getResources().getColor(R.color.charcoal_grey));
            description.setCompoundDrawablesWithIntrinsicBounds(null,
                    null, null, null);
            description.setGravity(View.TEXT_ALIGNMENT_CENTER);
            description.setText(Html.fromHtml(str));
        }

        // костыль по добавлению изображения в TextVIew
        private void descriptionBad(Date date) {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            String str = layout.getContext().getResources().getString(R.string.discription_bad, format.format(date));
            int padding_8dp = (int) layout.getContext().getResources().getDimension(R.dimen.padding_drawable);
            description.setText(str);
            description.setTextColor(layout.getResources().getColor(R.color.coral));
            Drawable drawable = layout.getResources().getDrawable(R.drawable.ic_alert);
            description.setCompoundDrawablePadding(padding_8dp);
            description.setCompoundDrawablesWithIntrinsicBounds(drawable,
                    null, null, null);
            description.setGravity(View.TEXT_ALIGNMENT_CENTER);
        }
    }
    public AdapterRecyclerViewCounter(ArrayList<DataCounter> dataCounters, AdapterClickListr adapterClickListr) {
        this.adapterClickListener = adapterClickListr;
        this.dataCounters = dataCounters;
    }

    @NonNull
    @Override
    public HolderCounter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderCounter(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_for_counter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCounter holder, int position) {
        holder.bindHolder(dataCounters.get(position), position, adapterClickListener, this);
    }

    @Override
    public int getItemCount() {
        return dataCounters.size();
    }



}
