public class Square {
    private Integer x;
    private Integer y;
    private Integer value;
    private Boolean isClue = false;
    private Integer subGridIndex;

    public Square(Integer x, Integer y, Integer value) {
        this.x = x;
        this.y = y;
        this.value = value;
        if(value != 0){
            isClue = true;
        }
        this.evaluateSubGridIndex();
    }

    private void evaluateSubGridIndex(){
        if(this.x < 3 && this.y < 3){
            this.subGridIndex = 1;
            return;
        }
        if(this.x < 6 && this.y < 3){
            this.subGridIndex = 2;
            return;
        }
        if(this.x < 9 && this.y < 3){
            this.subGridIndex = 3;
            return;
        }

        if(this.x < 3 && this.y < 6){
            this.subGridIndex = 4;
            return;
        }
        if(this.x < 6 && this.y < 6){
            this.subGridIndex = 5;
            return;
        }
        if(this.x < 9 && this.y < 6){
            this.subGridIndex = 6;
            return;
        }

        if(this.x < 3 && this.y < 9){
            this.subGridIndex = 7;
            return;
        }
        if(this.x < 6 && this.y < 9){
            this.subGridIndex = 8;
            return;
        }
        if(this.x < 9 && this.y < 9){
            this.subGridIndex = 9;
            return;
        }
    }

    public Integer getX() {
        return this.x;
    }

    public Integer getY() {
        return this.y;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Boolean getIsClue() {
        return this.isClue;
    }

    public Integer getSubGridIndex() {
        return this.subGridIndex;
    }
}
