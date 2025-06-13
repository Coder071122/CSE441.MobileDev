package vn.edu.tlu.cse.ht2.ngovanphat.qlpk.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.R;
import vn.edu.tlu.cse.ht2.ngovanphat.qlpk.model.Appointment;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {
    private List<Appointment> appointments;
    private boolean isDoctor;
    private AppointmentClickListener listener;

    public interface AppointmentClickListener {
        void onConfirmClick(Appointment appointment);
        void onCancelClick(Appointment appointment);
    }

    public AppointmentAdapter(List<Appointment> appointments, boolean isDoctor, AppointmentClickListener listener) {
        this.appointments = appointments;
        this.isDoctor = isDoctor;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_appointment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment appointment = appointments.get(position);

        holder.tvDate.setText("Ngày: " + appointment.getDate());
        holder.tvTime.setText("Giờ: " + appointment.getTime());
        holder.tvDoctor.setText("Bác sĩ: " + appointment.getDoctorEmail());
        holder.tvPatient.setText("Bệnh nhân: " + appointment.getPatientEmail());
        holder.tvStatus.setText("Trạng thái: " + appointment.getStatus());
        holder.tvNotes.setText("Ghi chú: " + appointment.getNotes());

        // Hiển thị nút theo vai trò và trạng thái
        if (isDoctor) {
            if ("Chưa xác nhận".equals(appointment.getStatus())) {
                holder.btnConfirm.setVisibility(View.VISIBLE);
                holder.btnCancel.setVisibility(View.VISIBLE);
            } else {
                holder.btnConfirm.setVisibility(View.GONE);
                holder.btnCancel.setVisibility(View.GONE);
            }
        } else {
            holder.btnConfirm.setVisibility(View.GONE);
            if (!"Đã hủy".equals(appointment.getStatus()) &&
                    !"Đã xác nhận".equals(appointment.getStatus())) {
                holder.btnCancel.setVisibility(View.VISIBLE);
            } else {
                holder.btnCancel.setVisibility(View.GONE);
            }
        }

        // Xử lý sự kiện click
        holder.btnConfirm.setOnClickListener(v -> {
            if (listener != null) {
                listener.onConfirmClick(appointment);
            }
        });

        holder.btnCancel.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCancelClick(appointment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public void updateAppointments(List<Appointment> newAppointments) {
        this.appointments = newAppointments;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvTime, tvDoctor, tvPatient, tvStatus, tvNotes;
        Button btnConfirm, btnCancel;

        ViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvDoctor = itemView.findViewById(R.id.tvDoctor);
            tvPatient = itemView.findViewById(R.id.tvPatient);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvNotes = itemView.findViewById(R.id.tvNotes);
            btnConfirm = itemView.findViewById(R.id.btnConfirm);
            btnCancel = itemView.findViewById(R.id.btnCancel);
        }
    }
}