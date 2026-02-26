package com.example.elytisapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private MediaPlayer mediaPlayer;

    // Προσθήκη στα πεδία της κλάσης
    private Handler audioHandler = new Handler(Looper.getMainLooper());
    private Runnable fadeOutRunnable;

    // Δεδομένα: Προσθέστε εδώ τα ID των αρχείων σας
    private final int[] images = {R.drawable.im1, R.drawable.im2, R.drawable.im3, R.drawable.im4,
            R.drawable.im5, R.drawable.im6, R.drawable.im7, R.drawable.im8, R.drawable.im9,
            R.drawable.im10, R.drawable.im11};
    private final int[] audio = {R.raw.p1, R.raw.p2, R.raw.p3, R.raw.p4, R.raw.p5, R.raw.p6,
            R.raw.p7, R.raw.p8, R.raw.p9, R.raw.p10, R.raw.p11};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ElytisAdapter());
        // Προσθήκη του Transition Effect εδώ:
        viewPager.setPageTransformer(new DepthPageTransformer());

        // Η καρδιά της λειτουργίας: Έλεγχος αλλαγής σελίδας
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                playAudio(position % images.length); // % για την ανακύκλωση
            }
        });
    }

    private void playAudio(int index) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            audioHandler.removeCallbacksAndMessages(null); // Ακύρωση προηγούμενων fade-out
        }

        mediaPlayer = MediaPlayer.create(this, audio[index]);
        mediaPlayer.start();

        // Υπολογισμός του χρόνου έναρξης του fade-out (3 δευτερόλεπτα πριν το τέλος)
        int duration = mediaPlayer.getDuration();
        int fadeStartDelay = duration - 3000;

        if (fadeStartDelay > 0) {
            fadeOutRunnable = new Runnable() {
                float volume = 1.0f;
                @Override
                public void run() {
                    if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                        volume -= 0.05f; // Μείωση έντασης κατά 5%
                        if (volume > 0) {
                            mediaPlayer.setVolume(volume, volume);
                            audioHandler.postDelayed(this, 150); // Επανάληψη κάθε 150ms
                        }
                    }
                }
            };
            audioHandler.postDelayed(fadeOutRunnable, fadeStartDelay);
        }
    }

    // Adapter για τη σύνδεση δεδομένων και ViewPager
    private class ElytisAdapter extends RecyclerView.Adapter<ElytisAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            int realPosition = position % images.length;
            holder.imageView.setImageResource(images[realPosition]);
        }

        @Override
        public int getItemCount() {
            // Για άπειρη ανακύκλωση, επιστρέφουμε έναν πολύ μεγάλο αριθμό
            return Integer.MAX_VALUE;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            ViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) mediaPlayer.release();
    }
}