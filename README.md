# Elytis Immersive: Exploring the Visual Universe of Odysseus Elytis
**A UI/UX Design for Digital Galleries: The Case of Odysseas Elytis' Collages.**

Μια διαδραστική οπτικοακουστική εφαρμογή Android αφιερωμένη στο εικαστικό έργο του **Οδυσσέα Ελύτη**. Η εφαρμογή συνδυάζει τα κολάζ του ποιητή με την απαγγελία του ποιήματος "Ελένη", προσφέροντας μια εμβυθιστική εμπειρία (immersive experience).

## 📖 Περιγραφή
Η εφαρμογή επιτρέπει στον χρήστη να περιηγηθεί σε 11 εικαστικά έργα (κολάζ) του Οδυσσέα Ελύτη. Καθώς ο χρήστης αλλάζει εικόνα, ο ήχος συγχρονίζεται αυτόματα με την αντίστοιχη στροφή του ποιήματος, ενώ ειδικά εφέ μετάβασης ενισχύουν την αίσθηση του βάθους και της καλλιτεχνικής ροής.

### Τεχνικά Χαρακτηριστικά
* **ViewPager2 με Infinite Scroll:** Αδιάκοπη πλοήγηση στις εικόνες.
* **Depth Page Transformer:** Προσαρμοσμένο εφέ μετάβασης που δίνει την αίσθηση ότι ο χρήστης "εισχωρεί" μέσα στο έργο τέχνης.
* **Synchronized Audio:** Αυτόματη έναρξη και ομαλό σβήσιμο (Fade-out) του ήχου σε κάθε αλλαγή σελίδας.
* **Long-Press Interaction:** Εμφάνιση των στίχων του ποιήματος πάνω από την εικόνα με παρατεταμένο πάτημα.

## 📂 Δομή Αρχείων Πολυμέσων

| Visual Artwork (Collages) | Audio Narration (Poems) |
| :--- | :--- |
| [im1.jpg](app/src/main/res/drawable/im1.jpg) | [p1.mp3](app/src/main/res/raw/p1.mp3) |
| [im2.jpg](app/src/main/res/drawable/im2.jpg) | [p2.mp3](app/src/main/res/raw/p2.mp3) |
| [im3.jpg](app/src/main/res/drawable/im3.jpg) | [p3.mp3](app/src/main/res/raw/p3.mp3) |
| [im4.jpg](app/src/main/res/drawable/im4.jpg) | [p4.mp3](app/src/main/res/raw/p4.mp3) |
| [im5.jpg](app/src/main/res/drawable/im5.jpg) | [p5.mp3](app/src/main/res/raw/p5.mp3) |
| [im6.jpg](app/src/main/res/drawable/im6.jpg) | [p6.mp3](app/src/main/res/raw/p6.mp3) |
| [im7.jpg](app/src/main/res/drawable/im7.jpg) | [p7.mp3](app/src/main/res/raw/p7.mp3) |
| [im8.jpg](app/src/main/res/drawable/im8.jpg) | [p8.mp3](app/src/main/res/raw/p8.mp3) |
| [im9.jpg](app/src/main/res/drawable/im9.jpg) | [p9.mp3](app/src/main/res/raw/p9.mp3) |
| [im10.jpg](app/src/main/res/drawable/im10.jpg) | [p10.mp3](app/src/main/res/raw/p10.mp3) |
| [im11.jpg](app/src/main/res/drawable/im11.jpg) | [p11.mp3](app/src/main/res/raw/p11.mp3) |

## 🛠 Οδηγίες Χρήσης

### 1. Περιήγηση (Navigation)
* **Swipe Left/Right:** Σύρετε το δάχτυλό σας δεξιά ή αριστερά για να αλλάξετε εικόνα και ηχητικό απόσπασμα.
* **Ήχος:** Ο ήχος ξεκινά αυτόματα. Αν αλλάξετε σελίδα πριν τελειώσει, η ένταση μειώνεται σταδιακά (fade-out) για μια ομαλή μετάβαση στο επόμενο απόσπασμα.

### 2. Εμφάνιση Στίχων (Lyrics Overlay)
* **Long Press (Παρατεταμένο Πάτημα):** Κρατήστε πατημένο το δάχτυλό σας πάνω σε οποιαδήποτε εικόνα για να εμφανιστούν οι αντίστοιχοι στίχοι του ποιήματος σε ημιδιαφανές πλαίσιο.
* **Simple Click (Απλό Πάτημα):** Πατήστε μία φορά πάνω στο κείμενο για να το αποκρύψετε και να επιστρέψετε στην πλήρη προβολή του κολάζ.

---
*Η εφαρμογή αναπτύχθηκε στο πλαίσιο μελέτης για την ψηφιακή ανάδειξη της ελληνικής λογοτεχνίας και τέχνης προκειμένου να επιτρέψει την διαδραστική ανάγνωση με χρήση πολυμέσων και AR.*
