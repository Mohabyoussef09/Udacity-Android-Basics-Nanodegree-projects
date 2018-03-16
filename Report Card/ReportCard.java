package com.example.android.reportcard;


public class ReportCard 
{
    private int StudentID;
    private String StudentName;
    private String Course;
    private int Grade;
    

    public ReportCard(int studentID, String studentName, String course, int grade)
	{
        StudentID = studentID;
        StudentName = studentName;
        Course = course;
        Grade = grade;
       
    }

    public int getID() 
	{
        return StudentID;
    }

    public void setID(int id)
	{
        StudentID = id;
    }

    public String getName() 
	{
        return StudentName;
    }

    public void setName(String name) 
	{
        StudentName = name;
    }

    public String getCourse() 
	{
        return Course;
    }

    public void setCourse(String course)
	{
        Course = course;
    }

    public int getGrade() 
	{
        return Grade;
    }

    public void setGrade(int grade) 
	{
        Grade = grade;
    }

    public String getLetterGrade(int grade)
	{

        String letterGrade;

        if(grade >=97){
            letterGrade = "A+";
        }else if (grade >= 93){
            letterGrade = "A";
        }else if (grade >= 87){
            letterGrade = "B+";
        }else if (grade >= 83){
            letterGrade = "B";
        }else if (grade >= 80){
            letterGrade = "B-";
		}else if (grade >= 77){
            letterGrade = "C+";
        }else if (grade >= 73){
            letterGrade = "C";
        }else if (grade >= 70){
            letterGrade = "C-";
        }else if (grade >= 67){
            letterGrade = "D+";
		}else if (grade >= 63){
            letterGrade = "D";
        }else if (grade >= 60){
            letterGrade = "D-";
        }else {
            letterGrade = "F";
        }

        return letterGrade;
	}

    @Override
    public String toString() 
	{
        StudentName=StudentName.substring(0,1).toUpperCase() + StudentName.substring(1).toLowerCase();
        return "Your report card :" +"ID:" + StudentID +", Name:" + StudentName  +", Course:" + Course + ", Grade=:" + getLetterGrade(Grade) ;
    }
}