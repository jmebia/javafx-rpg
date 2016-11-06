package main.framework.levels.quests;


public enum QuestList {

    QUEST1("quest title here", "quest description here");

    String questTitle;
    String questDescription;
    int questSate = 0;

    QuestList(String questTitle, String questDescription) {
        this.questTitle = questTitle;
        this.questDescription = questDescription;
    }

}
