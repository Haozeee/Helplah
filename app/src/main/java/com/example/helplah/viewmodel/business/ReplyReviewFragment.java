package com.example.helplah.viewmodel.business;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.helplah.R;
import com.example.helplah.models.Review;

public class ReplyReviewFragment extends Fragment {

    private Review review;
    private TextView title;
    private TextView text;
    private TextView reviewText;
    private EditText reply;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        this.review = getArguments().getParcelable("review");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reply_review_fragment, container, false);
        this.title = rootView.findViewById(R.id.replyReviewUserNameTitle);
        this.text = rootView.findViewById(R.id.replyReviewUserNameText);
        this.reviewText = rootView.findViewById(R.id.replyReviewReviewText);
        this.reply = rootView.findViewById(R.id.reviewReply);

        bindData();

        Button replyButton = rootView.findViewById(R.id.replyReviewButton);
        replyButton.setOnClickListener(v -> replyToReview());

        return rootView;
    }

    @SuppressLint("SetTextI18n")
    private void bindData() {
        this.title.setText(this.review.getUsername() + "'s review");
        this.text.setText(this.review.getUsername() + " review:");
        this.reviewText.setText(this.review.getReviewText());
        if (this.review.getReply() != null) {
            this.reply.setText(this.review.getReply());
        }
    }

    private void replyToReview() {
        if (this.reply.length() == 0) {
            this.reply.setError("Please leave a short reply");
            return;
        }
        String replyText = this.reply.getText().toString().trim();
        this.review.setReply(replyText);
        Review.replyReview(this.review.getReviewId(), replyText, this.review.getBusinessId(), requireActivity());
    }
}