package com.example.elytisapp;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class DepthPageTransformer implements ViewPager2.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(@NonNull View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // Η σελίδα είναι πολύ αριστερά, εκτός οθόνης
            view.setAlpha(0f);

        } else if (position <= 0) { // [-1,0]
            // Χρήση της προεπιλεγμένης μετάβασης για την ολίσθηση προς τα αριστερά
            view.setAlpha(1f);
            view.setTranslationX(0f);
            view.setScaleX(1f);
            view.setScaleY(1f);

        } // Μέσα στη μέθοδο transformPage, στο block: else if (position <= 1)
        else if (position <= 1) { // (0,1]
            // Fade out με καμπύλη για να φαίνεται ότι διαρκεί περισσότερο
            // Χρησιμοποιούμε Math.pow για πιο ομαλή μετάβαση
            float alpha = (float) Math.pow(1 - position, 2);
            view.setAlpha(alpha);

            // Αντιστάθμιση ολίσθησης
            view.setTranslationX(pageWidth * -position);

            // Κλιμάκωση (Scale)
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        } else { // (1,+Infinity]
            // Η σελίδα είναι πολύ δεξιά, εκτός οθόνης
            view.setAlpha(0f);
        }
    }
}
