package main.framework.levels.quests;


public enum QuestList {

    QUEST1("title", "quest description"),
    EXAMPLEQUEST1("Training", "Collect the bacons located on top of the mountain.");
    // add more quests as much as you want

    String questTitle;
    String questDescription;
    int questSate = 0;

    QuestList(String questTitle, String questDescription) {
        this.questTitle = questTitle;
        this.questDescription = questDescription;
    }

}
