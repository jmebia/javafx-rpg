package main.framework.levels.quests;


public enum QuestList {

    QUEST1("The Escape", "Looks like I will be interrogated again. There's no way I'm getting out of here.");

    String questTitle;
    String questDescription;
    int questSate = 0;

    QuestList(String questTitle, String questDescription) {
        this.questTitle = questTitle;
        this.questDescription = questDescription;
    }

}
