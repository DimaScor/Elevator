package Entities;

public class Queue {
    import javax.persistence.*;

    @Entity
    public class Quote {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String text;
        private int votes;

        public Quote() { }

        public Quote(String text, int votes) {
            this.text = text;
            this.votes = votes;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getVotes() {
            return votes;
        }

        public void setVotes(int votes) {
            this.votes = votes;
        }
    }

}
