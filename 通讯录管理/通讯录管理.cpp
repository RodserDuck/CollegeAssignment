#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int numyes=0;
typedef struct member     
{
	char name[15];     
	int phonenumber;     
	struct member *lnext;     //ָ����߶�����
	struct member *rnext;     //ָ���ұ߶�����
}Txl;
Txl *insert_member(Txl *tree,Txl *txl)
{
	Txl *temp;     //����ָ��������е�ǰ��λ��
	Txl *previous;     //����ָ���������ǰһ����λ��
	temp = tree;     //һ��ʼָ��������ĸ���
	previous = temp;     //һ��ʼָ��������ĸ���
	while (temp != NULL)     //����ǰλ��ΪNULL�����ʾѰ�ҵ���ͷ�ĺ���λ��
	{
		previous = temp;     //ǰһָ��ָ��ǰָ�룬Ϊ��ǰָ���ƶ����ñ���
		if (strcmp(temp->name, txl->name) > 0)     //�Ƚ������ַ������������е��ַ����ϴ���������
		{
			temp = temp->lnext;     //��ǰָ��������
		}
		else
		{
			temp = temp->rnext;     //����������
		}
	}
	if (strcmp(previous->name, txl->name) > 0)     //tempΪNULL�ˣ���previousΪ�ҵ��ĺ��ʵ�λ�ã���Ҫ�ٱȽ�һ���ַ���
	{
		previous->lnext=txl;     //���������е��ַ����ϴ����������
	}
	else
	{
		previous->rnext=txl;     //���������е��ַ�����С���������ұ�
	}
	return tree;
}
Txl *create_member(Txl *tree)
{    
	int y;//Ҫ��ӵ���ϵ�˸��� 
	printf("������Ҫ��������ϵ�˵ĸ�����\n\n");
	scanf("%d",&y);
	for(int i=0;i<y;i++){
	Txl *s = (Txl *)malloc(sizeof(Txl));     //����һ���ṹ��
	system("cls");     //����
	printf("������");
	scanf("%s",&s->name , 15);     //��ȡ����
	printf("�绰��");
	scanf("%d",&s->phonenumber);    
	s->lnext = NULL;
	s->rnext = NULL;
	system("cls");
	printf("������ɣ�\n\n");
	insert_member(tree, s);  //������Ĳ��������
	}   
	return tree;
}
void deleta_m(Txl *father, Txl *current, char input[15], int direction)
{
	if (current == NULL)    //��������Ϊ���򷵻�
	{
		return;
	}
	if (strcmp(current->name, input) == 0)    //��������ͬ��Ϊָ����ϵ��
	{	
		int m;
		printf("��ѯ������ϵ�˵绰��%d\n",current->phonenumber);
		printf("�Ƿ�ΪҪɾ��(���޸�)����ϵ�ˣ�\n1.��  2.��(����)\n");
		scanf("%d",&m);
		if(m==0)
		{
			return ;
		}
		if(m==1)
		{
		  
		Txl *temp;    //����ָ��������е�ǰ��λ��
		Txl *previous;    //����ָ���������ǰһ����λ��
		temp = current->lnext;    //ָ��ǰָ�������Ŀ���ǰѵ�ǰָ��������뵽���
		previous = current;    //ǰһָ��ָ���ҵ��Ľṹ��
		while (temp != NULL)     //��Ϊ����Ϊ�ҵ������ұ�
		{
			previous = temp;     //ǰһָ��ָ��ǰָ�룬���浱ǰλ�ã��Ա��������ǰָ��������
			temp = temp->rnext;    //��ǰָ��ָ���ұ�
		}
		previous->rnext = current->rnext;    //ǰһָ��ָ��Ϊ���ҵĽṹ�壬��Ҫɾ���Ľṹ�����ָ�����ǰһָ����ұ�
		if (direction == 1)    //��Ϊ1����Ϊ��ָ����ϵ���ڶ���������ǰһָ������
		{
			father->lnext = current->lnext;    //ָ����ϵ�˵�ǰһָ�����ָ��ָ��ָ����ϵ��ָ�����ߣ���������ָ����ϵ��
		}
		else
		{
			father->rnext = current->lnext;    //ָ����ϵ�˵�ǰһָ�����ָ��ָ��ָ����ϵ��ָ�����ߣ���������ָ����ϵ��
		}
		free(current);    //��ָ����ϵ���ͷ�
		printf("ɾ���ɹ�\n");
		return ;
		}
	}
	deleta_m(current, current->lnext, input, 1);    //�Ե�ǰָ�롢��ǰָ������Ϊ���������ݹ飬����1��ʾ����Ϊ���
	deleta_m(current, current->rnext, input, 2);    //�Ե�ǰָ�롢��ǰָ����ұ�Ϊ���������ݹ飬����2��ʾ����Ϊ�ұ�
}
Txl *deleta_member(Txl *tree)
{
	char input[15];     //��Ų�ѯ������
	system("cls");
	printf("������������������������������������������������\n");
		printf("������Ҫɾ������ϵ��������");
		scanf("%s", input, 15);     //��ȡ��Ҫ��ѯ������
		if (strcmp(tree->name, input) == 0)     //�Ƚ϶������ĸ�����ϵ����������Ҫɾ������ϵ��Ϊ��������Ҫ���¸�
		{
			Txl *temp;     //ָ��ǰ�ṹ��
			Txl *previous;     //ָ��ǰһ�ṹ��
			Txl *newt;     //Ϊ�µĸ�
			temp = tree->lnext;     //��ǰָ��ָ��������
			printf("ɾ���ɹ�\n");
			if (temp == NULL)
			{
				temp = tree->rnext;
				if (temp == NULL)
				{
					tree->phonenumber = 0;     //������־
					free(tree->name);
					return tree;     //���ұ�ҲΪ�գ��򷵻ص��ǿ���
				}
				else
				{
					free(tree);     //��ԭ���ͷ�
					return temp;     //�����Ϊ����ֱ�ӷ����ұߣ�
				}
			}
			else
			{
				newt = tree->lnext;     //�Ը������Ϊ�¸�
				previous = tree;     //ǰһָ��ָ���������temp��ΪNULL���ػ�ִ������һ�α���
				while (temp != NULL)     //��ΪNULL����Ϊ�ҵ������ҵĽṹ��
				{
					previous = temp;     //��ǰָ��ָ��temp��Ϊtemp�����������ñ���
					temp = temp->rnext;     //��ǰָ������
				}
				previous->rnext = tree->rnext;     //ǰһָ��Ϊ�¶��������ұߣ���ԭ�������ұ߲����ұ�
				free(tree);     //��ԭ���ͷ�
				return newt;     //�����¶�����
			}
		}
		else
		{
			deleta_m(NULL, tree, input, 1);     //ɾ���ķǸ������ú�����ѯɾ��
			return tree;
		}
}
	

void find_m(Txl *father, Txl *current, char input[15], int direction)
{
	if (current == NULL)    //��������Ϊ���򷵻�
	{
		return;
	}
	if (strcmp(current->name, input) == 0)    //��������ͬ��Ϊָ����ϵ��
	{
		Txl *temp;    //����ָ��������е�ǰ��λ��
		Txl *previous;    //����ָ���������ǰһ����λ��
		temp = current->lnext;    //ָ��ǰָ�������Ŀ���ǰѵ�ǰָ��������뵽���
		previous = current;    //ǰһָ��ָ���ҵ��Ľṹ��
		while (temp != NULL)     //��Ϊ����Ϊ�ҵ������ұ�
		{
			previous = temp;     //ǰһָ��ָ��ǰָ�룬���浱ǰλ�ã��Ա��������ǰָ��������
			temp = temp->rnext;    //��ǰָ��ָ���ұ�
		}
		
		printf("��ѯ�ɹ�\n");
		printf("�绰��%d\n",current->phonenumber);
		numyes=1;
		
	}
	find_m(current, current->lnext, input, 1);    //�Ե�ǰָ�롢��ǰָ������Ϊ���������ݹ飬����1��ʾ����Ϊ���
	find_m(current, current->rnext, input, 2);    //�Ե�ǰָ�롢��ǰָ����ұ�Ϊ���������ݹ飬����2��ʾ����Ϊ�ұ�
}
void find_m_by_p(Txl *father, Txl *current, int x, int direction)
{
	if (current == NULL)    //��������Ϊ���򷵻�
	{
		return;
	}
	if (current->phonenumber == x)    //��������ͬ��Ϊָ����ϵ��
	{
		Txl *temp;    //����ָ��������е�ǰ��λ��
		Txl *previous;    //����ָ���������ǰһ����λ��
		temp = current->lnext;    //ָ��ǰָ�������Ŀ���ǰѵ�ǰָ��������뵽���
		previous = current;    //ǰһָ��ָ���ҵ��Ľṹ��
		while (temp != NULL)     //��Ϊ����Ϊ�ҵ������ұ�
		{
			previous = temp;     //ǰһָ��ָ��ǰָ�룬���浱ǰλ�ã��Ա��������ǰָ��������
			temp = temp->rnext;    //��ǰָ��ָ���ұ�
		}
		
		printf("��ѯ�ɹ�\n");
		printf("������%s\n",current->name);
		numyes=1;
		
	}
	find_m_by_p(current, current->lnext, x, 1);    //�Ե�ǰָ�롢��ǰָ������Ϊ���������ݹ飬����1��ʾ����Ϊ���
	find_m_by_p(current, current->rnext, x, 2);    //�Ե�ǰָ�롢��ǰָ����ұ�Ϊ���������ݹ飬����2��ʾ����Ϊ�ұ�
}
Txl *find_member(Txl *tree)
{
	char input[15];     //��Ų�ѯ������
	int x;
	system("cls");
	printf("������������������������������������������������\n");
		printf("�������ѯ��ʽ��������绰����\n1.����  2.�绰\n");
		scanf("%d",&x);
		if(x==1)
		{
		printf("������Ҫ��ѯ����ϵ��������");
		scanf("%s", input, 15);     
		find_m(NULL, tree, input, 1);    
		} 
		else
		{
		printf("������Ҫ��ѯ����ϵ�˵绰��");
		scanf("%d",&x);
		find_m_by_p(NULL, tree, x, 1);    	
		}
		return tree;
}
	
Txl *modify_member(Txl *tree){
	char p[15];
	printf("Ҫ�޸ĵ���ϵ��������\n");
	scanf("%s",&p , 15);
	find_m(NULL, tree, p, 1);
	if(numyes==0)
		{
			printf("��ѯ����ϵ�˲����ڣ�\n");
			return tree;
		}
	else
		{
			numyes=0;
		}
	Txl *s = (Txl *)malloc(sizeof(Txl));     //����һ���ṹ��
	printf("�޸ĺ���ϵ��������\n");
	scanf("%s",&s->name , 15);     //��ȡ����
	printf("�޸ĺ���ϵ�˵绰��");
	scanf("%d",&s->phonenumber);    
	s->lnext = NULL;
	s->rnext = NULL;
	deleta_m(NULL, tree,p , 1);
	insert_member(tree, s);
	system("cls");
	printf("�޸���ɣ�\n\n");
	return tree;
}
void member_printf(Txl *tree)
{
	if (tree == NULL)     //��ΪNULL�򷵻�
	{
		return;
	}
	member_printf(tree->lnext);     //�����ߵݹ�
	if(tree->phonenumber!=0)
	{
		printf("������������������������������������������������\n");
		printf("������%s", tree->name);  //��ӡ
		printf("          �绰��%d\n", tree->phonenumber);  
	}  
	member_printf(tree->rnext);     //�����ߵݹ�
	return;
}
void free_tree(Txl *tree)
{
	if (tree == NULL)     //��ΪNULL�򷵻�
	{
		return;
	}
	free_tree(tree->lnext);     //����߼����ݹ�
	free_tree(tree->rnext);     //���ұ߼����ݹ�
	free(tree);     //�ͷŽṹ��
}

int main(void)
{
	system("color f0");
	int o=0;     //���ڴ�Ų�����
	Txl *tree=(Txl *)malloc(sizeof(Txl));      //������ϵ�˽ṹ�壬��Ϊ��������
	tree->phonenumber = 0;     //�绰Ϊ0����ʾ����
	tree->lnext = NULL;     //�ṹ������ָ���ָ
	tree->rnext = NULL;
	while (1)
	{	
		printf("������������������������������������������������������������������\n");
		printf("�� 1 �����ϵ����Ϣ               ��\n");
		printf("�� 2 ɾ����ϵ����Ϣ               ��\n");
		printf("�� 3 �޸���ϵ����Ϣ               ��\n");
		printf("�� 4 ��ѯ��ϵ����Ϣ               ��\n");
		printf("�� 5 ������˳�����ͨѶ¼������Ϣ ��\n"); 
		printf("�� 6 �˳�ϵͳ                     ��\n");
		printf("������������������������������������������������������������������\n");
		printf("    ���������Ĳ�����");
		scanf("%d", &o);     //��ȡ������
		switch (o)
		{
		case 1:tree = create_member(tree); break;     //�����ϵ����Ϣ
		case 2:tree = deleta_member(tree); break;     //ɾ����ϵ����Ϣ
		case 3:tree = modify_member(tree); break;     
		case 4:find_member(tree); 
				if(numyes==0)
					{
						printf("��ѯ����ϵ�˲����ڣ�\n");
					}
				else
					{
						numyes=0;
					}
				break;     //ȫ����ϵ�˳ɼ���ѯ
		case 5:member_printf(tree); break;
		default:break;
		}
		if (o==6)     //������ѭ��
		{
			break;
		}
	}
	free_tree(tree);     //���������ͷ�
	system("pause");
	return 0;
}
