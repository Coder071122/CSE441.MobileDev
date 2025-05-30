package com.example.demo_android_api;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import retrofit2.Callback;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_android_api.Models.Player;
import com.example.demo_android_api.Services.PlayerService;
import com.example.demo_android_api.api.ApiClient;
import androidx.appcompat.app.AlertDialog;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    PlayerService playerService;
    List<Player> players = new ArrayList<>();
    PlayerAdapter adapter;
    RecyclerView recyclerView;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerService = ApiClient.getRetrofit().create(PlayerService.class);

        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new PlayerAdapter(this, players, this::loadPlayers);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> showAddDialog());

        loadPlayers();
    }

    public void loadPlayers() {
        playerService.getPlayers().enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                players.clear();
                if (response.isSuccessful()) {
                    players.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thêm hội viên");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        EditText edtName = new EditText(this);
        edtName.setHint("Tên hội viên");
        layout.addView(edtName);

        builder.setView(layout);
        builder.setPositiveButton("Thêm", (dialog, which) -> {
            Player p = new Player();
            p.username = edtName.getText().toString();
            p.avatar = "default.jpg";
            p.hometown = "Hà Nội";
            p.residence = "TP.HCM";
            p.birthday = "2005-10-10";
            p.rating_single = 0;
            p.rating_double = 0;

            playerService.createPlayer(p).enqueue(new Callback<Player>() {
                @Override
                public void onResponse(Call<Player> call, Response<Player> response) {
                    Toast.makeText(MainActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                    loadPlayers();
                }

                @Override
                public void onFailure(Call<Player> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Lỗi thêm", Toast.LENGTH_SHORT).show();
                }
            });
        });

        builder.setNegativeButton("Hủy", null);
        builder.show();
    }

    public void showEditDialog(Player player) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sửa hội viên");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        EditText edtName = new EditText(this);
        edtName.setText(player.username);
        layout.addView(edtName);

        builder.setView(layout);
        builder.setPositiveButton("Cập nhật", (dialog, which) -> {
            player.username = edtName.getText().toString();
            playerService.updatePlayer(player.id, player).enqueue(new Callback<Player>() {
                @Override
                public void onResponse(Call<Player> call, Response<Player> response) {
                    Toast.makeText(MainActivity.this, "Đã cập nhật", Toast.LENGTH_SHORT).show();
                    loadPlayers();
                }

                @Override
                public void onFailure(Call<Player> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
                }
            });
        });

        builder.setNegativeButton("Hủy", null);
        builder.show();
    }
}
