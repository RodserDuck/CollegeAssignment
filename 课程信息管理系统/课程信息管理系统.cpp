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
    printf("��������Ҫ����γ̸���:\n");
    scanf("%d",&a);
    for(b=0;b<a;b++)
	{
	printf("������γ̱��:\n");
    scanf("%d", &new_course.course_id);  
	printf("������γ�����:\n");
    scanf("%s", new_course.course_name);  
	printf("������γ̿�ʱ��:\n");  
    scanf("%d", &new_course.hours);  
	printf("������γ��Ͽ�ѧ��:\n");
    scanf("%d", &new_course.semester);  
	printf("������γ�����(ѡ�޻����):\n"); 
    scanf("%s", new_course.nature);  
    courses[course_count++] = new_course;  
	}
}  
  
void search_all() {  
    for (int i = 0; i < course_count; i++) {  
        printf("�γ̱��: %d\n", courses[i].course_id);
        printf("�γ�����: %s\n", courses[i].course_name);
        printf("�γ̿�ʱ��: %d\n", courses[i].hours);
        printf("�γ��Ͽ�ѧ�� %d\n", courses[i].semester);
        printf("�γ�����: %s\n", courses[i].nature);  
        printf("\n");  
    }  
}  
  
void search_by_name() { 
 	char cname[50];
 	int i,j=0;
	printf("������Ҫ��ѯ�Ŀγ����ƣ�");
 	scanf("%s", &cname);
    printf("�γ����� '%s':\n", cname);  
    for (i = 0; i < course_count; i++) 
	{  
        if (strcmp(courses[i].course_name, cname) == 0) 
		{  
			printf("��ѯ�ɹ�\n"); 
            printf("����ѯ�Ŀγ̱��: %d\n", courses[i].course_id);  
            printf("        �γ̿�ʱ��: %d\n", courses[i].hours);  
            printf("        �γ��Ͽ�ѧ��: %d\n", courses[i].semester);  
            printf("        �γ�����: %s\n", courses[i].nature);  
            printf("\n");
			j++;  
        }  
    }  
    if(i==course_count&&j== 0)
    {
    	 printf("û���ҵ�����ѯ�Ŀγ�!\n");
	}
}  
  
void search_by_nature() {
	char cnature[10];
	int i,j=0;
	printf("������������ѯ�Ŀγ�����(ѡ�޻����):\n");
	scanf("%s", &cnature);
    printf("�γ����� '%s':\n", cnature);  
    for (i = 0; i < course_count; i++) 
	{  
        if (strcmp(courses[i].nature, cnature) == 0) 
		{  
            printf("�γ̱��: %d\n", courses[i].course_id);  
            printf("�γ�����: %s\n", courses[i].course_name);  
            printf("�γ̿�ʱ��: %d\n", courses[i].hours);  
            printf("�γ�����: %d\n", courses[i].semester);  
            printf("\n");
			j++;  
        }  
    } 
	if(i==course_count&&j==0)
    {
    	 printf("û���ҵ�����ѯ�Ŀγ�!\n");
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
	printf("������Ҫ�޸ĵĿγ����ƣ�");
 	scanf("%s", &cname);
 	printf("��ȷ�ϸÿγ����Ƶı�ţ�");
	scanf("%d",&x);
    for (int i = 0; i < course_count; i++) 
	{  
        if (strcmp(courses[i].course_name, cname) == 0&&courses[i].course_id==x) 
		{   			  
			printf("�������޸ĺ�Ŀγ�ѧʱ�� ");  
            scanf("%d", &courses[i].hours);  
            printf("�������޸ĺ�Ŀγ�ѧ�ڣ� ");  
            scanf("%d", &courses[i].semester);  
            printf("�������޸ĺ�γ����ʣ�ѡ�޻���ޣ�: ");  
            scanf("%s", courses[i].nature);  
           	printf("�γ� '%s'�Ѿ��޸ĳɹ�.\n", cname);  
           	return;  
		}
    }  
    printf("�γ� '%s' δ�ҵ���\n", cname);  
}
int main()
{
	int n;
	char a=1;
	while(a==1){
	printf("��ӭʹ�ÿγ���Ϣ����ϵͳ\n");
	printf("��ѡ����Ҫʹ�õĹ���:\n");
	printf("1.����γ���Ϣ\n2.��ѯȫ���γ���Ϣ.\n3.���γ�����ѯ�γ���Ϣ\n4.���γ����ʲ�ѯ�γ���Ϣ\n5.����γ�\n6.�޸Ŀγ�\n7.�˳�\n");
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
		printf("�������!");
		break;
	}
	printf("���\n");
	}
	return 0;
}
