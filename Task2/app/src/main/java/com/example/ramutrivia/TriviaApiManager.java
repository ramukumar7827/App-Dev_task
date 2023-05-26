package com.example.ramutrivia;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TriviaApiManager {
    private static final String API_URL = "https://opentdb.com/api.php?amount=10&category=21&difficulty=medium&type=multiple";
    private OkHttpClient client;

    public TriviaApiManager() {
        client = new OkHttpClient();
    }

    public interface TriviaApiCallback {


        void onQuestionsReceived(List<Question> questions);

        void onApiError(String error);
    }

    public void getQuestions(int amount, String category, String difficulty, TriviaApiCallback callback) {

        String url = buildUrl(amount, category, difficulty);


        Request request = new Request.Builder()
                .url(url)
                .build();


        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

                callback.onApiError(e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {

                        String jsonData = response.body().string();
                        List<Question> questions = parseQuestions(jsonData);

                        callback.onQuestionsReceived(questions);
                    } catch (JSONException e) {

                        callback.onApiError(e.getMessage());
                    }
                } else {

                    callback.onApiError(response.message());
                }
            }
        });
    }

    private String buildUrl(int amount, String category, String difficulty) {
        return API_URL + "?amount=" + amount + "&category=" + category + "&difficulty=" + difficulty;
    }

    private List<Question> parseQuestions(String jsonData) throws JSONException {
        List<Question> questions = new ArrayList<>();

        JSONObject responseObj = new JSONObject(jsonData);
        JSONArray resultsArray = responseObj.getJSONArray("results");

        for (int i = 0; i < resultsArray.length(); i++) {
            JSONObject questionObj = resultsArray.getJSONObject(i);

            String questionText = questionObj.getString("question");
            String correctAnswer = questionObj.getString("correct_answer");

            JSONArray incorrectAnswersArray = questionObj.getJSONArray("incorrect_answer");
            List<String> incorrectAnswers = new ArrayList<>();
            for (int j = 0; j < incorrectAnswersArray.length(); j++) {
                incorrectAnswers.add(incorrectAnswersArray.getString(j));
            }

            Question question = new Question(questionText, correctAnswer, incorrectAnswers);
            questions.add(question);
        }

        return questions;
    }
}
