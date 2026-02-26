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

    private String[] poemTexts = {
            "Οδυσσέας Ελύτης - ΕΛΕΝΗ",
            "Με την πρώτη σταγόνα της βροχής σκοτώθηκε το καλοκαίρι Μουσκέψανε τα λόγια που είχανε γεννήσει αστροφεγγιές Όλα τα λόγια που είχανε μοναδικό τους προορισμόν Εσένα!",
            "Κατά πού θ’ απλώσουμε τα χέρια μας τώρα που δε μας λογαριάζει πια ο καιρός Κατά πού θ’ αφήσουμε τα μάτια μας τώρα που οι μακρινές γραμμές ναυάγησαν στα σύννεφα Τώρα που κλείσανε τα βλέφαρά σου απάνω στα τοπία μας",
            "Κι είμαστε —σαν να πέρασε μέσα μας η ομίχλη—Μόνοι ολομόναχοι τριγυρισμένοι απ’ τις νεκρές εικόνες σου.",
            "Με το μέτωπο στο τζάμι αγρυπνούμε την καινούρια οδύνη Δεν είναι ο θάνατος που θα μας ρίξει κάτω μια που Εσύ υπάρχεις Μια που υπάρχει αλλού ένας άνεμος για να σε ζήσει ολάκερη",
            "Να σε ντύσει από κοντά όπως σε ντύνει από μακριά η ελπίδα μας. Μια που υπάρχει αλλού Καταπράσινη πεδιάδα πέρ’ από το γέλιο σου ώς τον ήλιο Λέγοντάς του εμπιστευτικά πως θα ξανασυναντηθούμε πάλι",
            "Όχι δεν είναι ο θάνατος που θ’ αντιμετωπίσουμε Παρά μια τόση δα σταγόνα φθινοπωρινής βροχής Ένα θολό συναίσθημα Η μυρωδιά του νοτισμένου χώματος μες στις ψυχές μας που όσο παν κι απομακρύνονται",
            "Κι αν δεν είναι το χέρι σου στο χέρι μας Κι αν δεν είναι το αίμα μας στις φλέβες των ονείρων σου Το φως στον άσπιλο ουρανό Κι η μουσική αθέατη μέσα μας",
            "ω! μελαγχολική Διαβάτισσα όσων μας κρατάν στον κόσμο ακόμα Είναι ο υγρός αέρας η ώρα του φθινοπώρου ο χωρισμός Το πικρό στήριγμα του αγκώνα στην ανάμνηση Που βγαίνει όταν η νύχτα πάει να μας χωρίσει από το φως",
            "Πίσω από το τετράγωνο παράθυρο που βλέπει προς τη θλίψη Που δε βλέπει τίποτε Γιατί έγινε κιόλας μουσική αθέατη φλόγα στο τζάκι χτύπημα του μεγάλου ρολογιού στον τοίχο Γιατί έγινε κιόλας Ποίημα",
            "στίχος μ’ άλλον στίχο αχός παράλληλος με τη βροχή δάκρυα και λόγια Λόγια όχι σαν τ’ άλλα μα κι αυτά μ’ ένα μοναδικό τους προορισμόν: Εσένα!"
    };

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
        // 1. Καθαρισμός προηγούμενου ήχου και ακύρωση του FadeOut
        audioHandler.removeCallbacksAndMessages(null);
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mediaPlayer = null;
        }

        // 2. Δημιουργία νέου ήχου
        mediaPlayer = MediaPlayer.create(this, audio[index]);
        if (mediaPlayer == null) return; // Προστασία αν λείπει το αρχείο

        mediaPlayer.start();

        // 3. Ασφαλές Fade Out
        int duration = mediaPlayer.getDuration();
        int fadeStartDelay = duration - 3000;

        if (fadeStartDelay > 0) {
            fadeOutRunnable = new Runnable() {
                float volume = 1.0f;
                @Override
                public void run() {
                    // Εδώ είναι η κρίσιμη διόρθωση: έλεγχος αν ο mediaPlayer είναι ακόμα "ζωντανός"
                    if (mediaPlayer != null) {
                        try {
                            volume -= 0.05f;
                            if (volume > 0) {
                                mediaPlayer.setVolume(volume, volume);
                                audioHandler.postDelayed(this, 150);
                            }
                        } catch (IllegalStateException e) {
                            // Αν ο mediaPlayer πέθανε ήδη, σταματάμε το Runnable
                            audioHandler.removeCallbacks(this);
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

            // Ορισμός του κειμένου από το poemTexts
            holder.poemTextView.setText(poemTexts[realPosition]);
            holder.poemTextView.setVisibility(View.GONE); // Αρχικά κρυφό

            // LONG PRESS: Εμφάνιση/Απόκρυψη στίχων
            holder.itemView.setOnLongClickListener(v -> {
                if (holder.poemTextView.getVisibility() == View.GONE) {
                    holder.poemTextView.setVisibility(View.VISIBLE);
                } else {
                    holder.poemTextView.setVisibility(View.GONE);
                }
                return true;
            });

            // Click: Απόκρυψη αν ο χρήστης απλώς πατήσει την οθόνη
            holder.itemView.setOnClickListener(v -> {
                holder.poemTextView.setVisibility(View.GONE);
            });
        }

        @Override
        public int getItemCount() { return Integer.MAX_VALUE; }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            android.widget.TextView poemTextView; // Πρέπει να το προσθέσετε στο item_page.xml

            ViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
                poemTextView = itemView.findViewById(R.id.poemTextView);
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) mediaPlayer.release();
    }
}