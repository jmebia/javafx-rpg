package main.framework.levels.quests;


public enum QuestList {

    QUEST1("title", "description");

    String questTitle;
    String questDescription;
    int questSate = 0;

    QuestList(String questTitle, String questDescription) {
        this.questTitle = questTitle;
        this.questDescription = questDescription;
    }

}