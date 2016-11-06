package main.framework.levels.quests;


public enum QuestList {

<<<<<<< HEAD
    QUEST1("quest title here", "quest description here");
=======
    QUEST1("title", "quest description"),
    EXAMPLEQUEST1("Training", "Collect the bacons located on top of the mountain.");
    // add more quests as much as you want
>>>>>>> origin/master

    String questTitle;
    String questDescription;
    int questSate = 0;

    QuestList(String questTitle, String questDescription) {
        this.questTitle = questTitle;
        this.questDescription = questDescription;
    }

}
