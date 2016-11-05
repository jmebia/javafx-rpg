package main.java;


public enum QuestList {

    QUEST1("Quest tile", "Quest description");

    String questTitle;
    String questDescription;
    int questSate = 0;

    QuestList(String questTitle, String questDescription) {
        this.questTitle = questTitle;
        this.questDescription = questDescription;
    }



}
