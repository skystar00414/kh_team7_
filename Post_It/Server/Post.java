package post_It.Server;

public class Post {

    // 게시판리스트 작성과 게시판 정보를 다루는 class 입니다.

    String post_type; // 게시판 종류
    String post_title; // 제목
    String category; // 말머리(카테고리)
    String content; // 내용
    String date_created; // 작성일자
    String writer_id; // 작성자 id
    int postNumber; // 1부터 감소하는 일 없이 쭈욱 증가할 예정입니다.
    // int poster_counter = postList.size(); => DB로!

    //기본 생성자
    public Post() {

    }

    //생성자
    public Post(String post_type, String post_title, String category, String content, String date_created,
            String writer_id, int postNumber) {
        this.post_type = post_type;
        this.post_title = post_title;
        this.category = category;
        this.content = content;
        this.date_created = date_created;
        this.writer_id = writer_id;
        this.postNumber = postNumber;
    }

    // 기본 메소드
    public void init() {

    }























    //getter, setter
    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getWriter_id() {
        return writer_id;
    }

    public void setWriter_id(String writer_id) {
        this.writer_id = writer_id;
    }

    public int getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }








    
}
