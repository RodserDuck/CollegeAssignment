#include <stdio.h>  
#include <stdlib.h>  
#include <string.h>  
  
#define MAX 100  
  
typedef struct {  
    int course_id;  
    char course_name[50];  
    int hours;  
    int semester;  
    char nature[10];  
} Course;  
 
Course courses[MAX];  
int course_count = 0;  
  
void input() {  
    Course new_course;  
    int a,b;
    printf("请输入您要输入课程个数:\n");
    scanf("%d",&a);
    for(b=0;b<a;b++)
	{
	printf("请输入课程编号:\n");
    scanf("%d", &new_course.course_id);  
	printf("请输入课程名称:\n");
    scanf("%s", new_course.course_name);  
	printf("请输入课程课时数:\n");  
    scanf("%d", &new_course.hours);  
	printf("请输入课程上课学期:\n");
    scanf("%d", &new_course.semester);  
	printf("请输入课程性质(选修或必修):\n"); 
    scanf("%s", new_course.nature);  
    courses[course_count++] = new_course;  
	}
}  
  
void search_all() {  
    for (int i = 0; i < course_count; i++) {  
        printf("课程编号: %d\n", courses[i].course_id);
        printf("课程名称: %s\n", courses[i].course_name);
        printf("课程课时数: %d\n", courses[i].hours);
        printf("课程上课学期 %d\n", courses[i].semester);
        printf("课程性质: %s\n", courses[i].nature);  
        printf("\n");  
    }  
}  
  
void search_by_name() { 
 	char cname[50];
 	int i,j=0;
	printf("请输入要查询的课程名称：");
 	scanf("%s", &cname);
    printf("课程名称 '%s':\n", cname);  
    for (i = 0; i < course_count; i++) 
	{  
        if (strcmp(courses[i].course_name, cname) == 0) 
		{  
			printf("查询成功\n"); 
            printf("所查询的课程编号: %d\n", courses[i].course_id);  
            printf("        课程课时数: %d\n", courses[i].hours);  
            printf("        课程上课学期: %d\n", courses[i].semester);  
            printf("        课程性质: %s\n", courses[i].nature);  
            printf("\n");
			j++;  
        }  
    }  
    if(i==course_count&&j== 0)
    {
    	 printf("没有找到所查询的课程!\n");
	}
}  
  
void search_by_nature() {
	char cnature[10];
	int i,j=0;
	printf("请输入您所查询的课程性质(选修或必修):\n");
	scanf("%s", &cnature);
    printf("课程性质 '%s':\n", cnature);  
    for (i = 0; i < course_count; i++) 
	{  
        if (strcmp(courses[i].nature, cnature) == 0) 
		{  
            printf("课程编号: %d\n", courses[i].course_id);  
            printf("课程名称: %s\n", courses[i].course_name);  
            printf("课程课时数: %d\n", courses[i].hours);  
            printf("课程性质: %d\n", courses[i].semester);  
            printf("\n");
			j++;  
        }  
    } 
	if(i==course_count&&j==0)
    {
    	 printf("没有找到所查询的课程!\n");
	} 
}  
  
void sort() {  
    for (int i = 0; i < course_count - 1; i++) {  
        for (int j = i + 1; j < course_count; j++) {  
            if (strcmp(courses[i].nature, courses[j].nature) > 0) {  
                Course temp = courses[i];  
                courses[i] = courses[j];  
                courses[j] = temp;  
            }  
        }  
    }  
}  
  
void modify() {  
	char cname[50];
	int x;
	printf("请输入要修改的课程名称：");
 	scanf("%s", &cname);
 	printf("请确认该课程名称的编号：");
	scanf("%d",&x);
    for (int i = 0; i < course_count; i++) 
	{  
        if (strcmp(courses[i].course_name, cname) == 0&&courses[i].course_id==x) 
		{   			  
			printf("请输入修改后的课程学时： ");  
            scanf("%d", &courses[i].hours);  
            printf("请输入修改后的课程学期： ");  
            scanf("%d", &courses[i].semester);  
            printf("请输入修改后课程性质（选修或必修）: ");  
            scanf("%s", courses[i].nature);  
           	printf("课程 '%s'已经修改成功.\n", cname);  
           	return;  
		}
    }  
    printf("课程 '%s' 未找到！\n", cname);  
}
int main()
{
	int n;
	char a=1;
	while(a==1){
	printf("欢迎使用课程信息管理系统\n");
	printf("请选择您要使用的功能:\n");
	printf("1.输入课程信息\n2.查询全部课程信息.\n3.按课程名查询课程信息\n4.按课程性质查询课程信息\n5.排序课程\n6.修改课程\n7.退出\n");
	scanf("%d",&n);
	switch(n)
	{
	case 1:
		input();
		break;
	case 2:
		search_all();
		break;
	case 3:
		search_by_name();
		break;
	case 4:
		search_by_nature();
		break;
	case 5:
		sort();
		break;
	case 6:
		modify();
		break;
	case 7: 
	    a=0;
	    break;
	default:
		printf("输入错误!");
		break;
	}
	printf("完成\n");
	}
	return 0;
}
