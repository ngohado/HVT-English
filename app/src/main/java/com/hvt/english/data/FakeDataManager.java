package com.hvt.english.data;

import com.hvt.english.model.Answer;
import com.hvt.english.model.Category;
import com.hvt.english.model.Question;
import com.hvt.english.model.Section;
import com.hvt.english.model.Sentence;
import com.hvt.english.model.Word;
import com.hvt.english.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class FakeDataManager implements IDataManager {

    public FakeDataManager(ApiClient apiClient) {

    }

    @Override
    public Observable<List<Category>> getCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "", "Yeu thich"));
        categories.add(new Category(1, "", "Chao Hoi"));
        categories.add(new Category(1, "", "Thuong Dung"));
        categories.add(new Category(1, "", "Con So"));
        categories.add(new Category(1, "", "Thoi Gian"));
        categories.add(new Category(1, "", "Du lich phuong huong"));
        categories.add(new Category(1, "", "Ket ban"));
        categories.add(new Category(1, "", "Gia Tri"));
        categories.add(new Category(1, "", "An Uong"));
        categories.add(new Category(1, "", "Mua Sam"));
        return Observable.just(categories);
    }

    @Override
    public Observable<List<Question>> getQuestion(int categoryID) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(1, "Hello hello1", new Answer("Answer A", false), new Answer("Answer B", true), new Answer("Answer C", false), new Answer("Answer D", false)));
        questions.add(new Question(1, "Hello hello2", new Answer("Answer A", false), new Answer("Answer B", true), new Answer("Answer C", false), new Answer("Answer D", false)));
        questions.add(new Question(1, "Hello hello3", new Answer("Answer A", false), new Answer("Answer B", true), new Answer("Answer C", false), new Answer("Answer D", false)));
        questions.add(new Question(1, "Hello hello4", new Answer("Answer A", false), new Answer("Answer B", true), new Answer("Answer C", false), new Answer("Answer D", false)));
        questions.add(new Question(1, "Hello hello5", new Answer("Answer A", false), new Answer("Answer B", true), new Answer("Answer C", false), new Answer("Answer D", false)));
        questions.add(new Question(1, "Hello hello6", new Answer("Answer A", false), new Answer("Answer B", true), new Answer("Answer C", false), new Answer("Answer D", false)));
        questions.add(new Question(1, "Hello hello7", new Answer("Answer A", false), new Answer("Answer B", true), new Answer("Answer C", false), new Answer("Answer D", false)));
        questions.add(new Question(1, "Hello hello8", new Answer("Answer A", false), new Answer("Answer B", true), new Answer("Answer C", false), new Answer("Answer D", false)));
        questions.add(new Question(1, "Hello hello9", new Answer("Answer A", false), new Answer("Answer B", true), new Answer("Answer C", false), new Answer("Answer D", false)));
        questions.add(new Question(1, "Hello hello10", new Answer("Answer A", false), new Answer("Answer B", true), new Answer("Answer C", false), new Answer("Answer D", false)));
        return Observable.just(questions);
    }

    @Override
    public int getTodayPoints() {
        return 100;
    }

    @Override
    public void savePoints(int points) {

    }

    @Override
    public int getStreakDay() {
        return 2;
    }

    @Override
    public Observable<Section> getDataSectionRemote(int categoryId) {
        List<Word> words = new ArrayList<>();
        words.add(new Word(1, "Hello", "Xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        words.add(new Word(1, "Hello", "Xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        words.add(new Word(1, "Hello", "Xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        words.add(new Word(1, "Hello", "Xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        words.add(new Word(1, "Hello", "Xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        words.add(new Word(1, "Hello", "Xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        words.add(new Word(1, "Hello", "Xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        words.add(new Word(1, "Hello", "Xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        words.add(new Word(1, "Hello", "Xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        List<Sentence> sentences = new ArrayList<>();
        sentences.add(new Sentence(1, "Hello Sentence", "Cau xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        sentences.add(new Sentence(1, "Hello Sentence", "Cau xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        sentences.add(new Sentence(1, "Hello Sentence", "Cau xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        sentences.add(new Sentence(1, "Hello Sentence", "Cau xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        sentences.add(new Sentence(1, "Hello Sentence", "Cau xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        sentences.add(new Sentence(1, "Hello Sentence", "Cau xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        sentences.add(new Sentence(1, "Hello Sentence", "Cau xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        sentences.add(new Sentence(1, "Hello Sentence", "Cau xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        sentences.add(new Sentence(1, "Hello Sentence", "Cau xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        sentences.add(new Sentence(1, "Hello Sentence", "Cau xin chao", "http://s1.vocaroo.com/media/download_temp/Vocaroo_s1OL9KjjUqGM.mp3", ""));
        return Observable.just(new Section(words, sentences));
    }
}
