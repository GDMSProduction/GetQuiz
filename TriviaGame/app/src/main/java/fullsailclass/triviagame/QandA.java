package fullsailclass.triviagame;

/**
 * Created by Jose Juan Otero Pere on 3/6/2018.
 */

public class QandA {

    private String Question; //Question should be on 0 in the list/array
    private String Answer1, Answer2,Answer3,Answer4; //Answers should be on their respective numbers ex = 1,2,3,4
    private Boolean IsAnswer1,IsAnswer2,IsAnswer3,IsAnswer4; //This is to check if this answer is the right one

        public QandA(String _Question, String _Answer1, String _Answer2, String _Answer3, String _Answer4){

            this.Question = _Question;
            this.Answer1 = _Answer1;
            this.Answer2 = _Answer2;
            this.Answer3 = _Answer3;
            this.Answer4 = _Answer4;

            IsAnswer1 = false;
            IsAnswer2 = false;
            IsAnswer3 = false;
            IsAnswer4 = false;
        }

    //region Gets
    public String getQuestion() {
        return Question;
    }

    public String getAnswer1() {
        return Answer1;
    }

    public String getAnswer2() {
        return Answer2;
    }

    public String getAnswer3() {
        return Answer3;
    }
    public String getAnswer4() {
        return Answer4;
    }
    public Boolean getIsAnswer1(){
        return IsAnswer1;
    }
    public Boolean getIsAnswer2(){
        return IsAnswer2;
    }
    public Boolean getIsAnswer3(){
        return IsAnswer3;
    }
    public Boolean getIsAnswer4(){
        return IsAnswer4;
    }
    //endregion

    //region Sets

    public void setQuestion(String question) {
        Question = question;
    }
    public void setAnswer1(String answer1) {
        Answer1 = answer1;
    }

    public void setAnswer2(String answer2) {
        Answer2 = answer2;
    }

    public void setAnswer3(String answer3) {
        Answer3 = answer3;
    }

    public void setAnswer4(String answer4) {
        Answer4 = answer4;
    }

    public void setIsAnswer4(Boolean answer4) {
        IsAnswer4 = answer4;
    }

    public void setIsAnswer3(Boolean answer3) {
        IsAnswer3 = answer3;
    }

    public void setIsAnswer2(Boolean answer2) {
        IsAnswer2 = answer2;
    }

    public void setIsAnswer1(Boolean answer1) {
        IsAnswer1 = answer1;
    }
    //endregion

}
