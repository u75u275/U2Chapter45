package U2Chapter45;

import javax.persistence.*;

@Entity
@Table(name = "TMP_STUDENT")
public class tmp_Student implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "STUDENTID")
    private long studentId;
    @Column(name = "STUDENTNAME")
    private String studentName;

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

}