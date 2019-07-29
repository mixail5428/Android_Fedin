package com.example.lesson_7_fedin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson_7_fedin.bridge.Bridge;
import com.example.lesson_7_fedin.bridge.Divorce;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class AdapterRecyclerViewBridges extends RecyclerView.Adapter<AdapterRecyclerViewBridges
        .HolderBridge> {

    List<Bridge> bridges;
    private ClickListener clickListener;

    interface ClickListener {
        void clickRecyclerView(Bridge bridge);
    }

    public static class HolderBridge extends RecyclerView.ViewHolder {
        Bridge bridge;
        View layout;
        ImageView imageViewStatus;
        TextView name;
        TextView divorce;
        boolean status;
        public static final boolean STATUS_OPEN = true;
        public static final boolean STATUS_CLOSE = false;


        public HolderBridge(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            imageViewStatus = itemView.findViewById(R.id.imageView_status);
            name = itemView.findViewById(R.id.textView_name);
            divorce = itemView.findViewById(R.id.textView_divorce);
        }

        public void bind(Bridge bridge, ClickListener clickListener) {
            this.bridge = bridge;
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null)
                        clickListener.clickRecyclerView(bridge);

                }
            });

            name.setText(bridge.getName());
            createDivorce();
            iconStatus();
        }

        private void createDivorce() {
            Iterator<Divorce> iterator = bridge.getDivorces().iterator();
            while (iterator.hasNext()) {
                Divorce item = iterator.next();
                String[] start = item.getStart().split(":");
                String[] end = item.getEnd().split(":");
                divorce.append(start[0] + ":" + start[1] + " - " + end[0] + ":" + end[1] + "   ");
            }
        }

        private void iconStatus() {
            Calendar now = new GregorianCalendar();
            Iterator<Divorce> iterator = bridge.getDivorces().iterator();
            while (iterator.hasNext()) {
                Divorce item = iterator.next();
                String[] start = item.getStart().split(":");
                String[] end = item.getEnd().split(":");

                // если хотя бы одна переменная не цифра то выходим из цикла
                if (!isFullNumber(start, end))
                    continue;

                Calendar timeStart = createCalendar(start);
                Calendar timeEnd = createCalendar(end);

                //контролирую чтобы start > end всегда
                if (timeStart.after(timeEnd))
                    timeEnd.add(Calendar.DAY_OF_YEAR, 1);

                if (now.after(timeStart) && now.after(timeEnd)) {
                    timeStart.add(Calendar.DAY_OF_YEAR, 1);
                    timeEnd.add(Calendar.DAY_OF_YEAR, 1);
                }

                if (now.after(timeStart) && now.before(timeEnd)) {
                    status = STATUS_CLOSE;
                    imageViewStatus.setImageResource(R.drawable.ic_brige_late);
                    break;
                }

                timeStart.add(Calendar.HOUR, -1);
                if (now.after(timeStart)) {
                    status = STATUS_OPEN;
                    imageViewStatus.setImageResource(R.drawable.ic_brige_soon);
                    break;
                }
                status = STATUS_OPEN;
                imageViewStatus.setImageResource(R.drawable.ic_brige_normal);

            }
        }

        public boolean isOpen() {
            return status;
        }

        public void dontClicable() {
            layout.setFocusable(false);
            layout.setClickable(false);
            layout.setBackground(null);
        }


        private Calendar createCalendar(String[] data) {
            Calendar calendar = new GregorianCalendar();
            calendar.set(Calendar.HOUR, Integer.parseInt(data[0]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(data[1]));
            calendar.set(Calendar.SECOND, 0);
            return calendar;
        }

        private boolean isFullNumber(String[] start, String[] end) {

            String regex = "\\d+";

            if (start[0].matches(regex) && start[1].matches(regex) && end[0].matches(regex) &&
                    end[1].matches(regex))
                return true;
            return false;
        }
    }

    public AdapterRecyclerViewBridges(List<Bridge> bridges, ClickListener clickListener) {
        this.bridges = bridges;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public HolderBridge onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderBridge(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_recycler_element_bridge, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderBridge holder, int position) {
        holder.bind(bridges.get(position), clickListener);

    }

    @Override
    public int getItemCount() {
        if (bridges.isEmpty())
            return 0;
        else return bridges.size();
    }
}
