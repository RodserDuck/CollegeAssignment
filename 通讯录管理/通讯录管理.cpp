#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int numyes=0;
typedef struct member     
{
	char name[15];     
	int phonenumber;     
	struct member *lnext;     //指向左边二叉树
	struct member *rnext;     //指向右边二叉树
}Txl;
Txl *insert_member(Txl *tree,Txl *txl)
{
	Txl *temp;     //用于指向二叉树中当前的位置
	Txl *previous;     //用于指向二叉树中前一个的位置
	temp = tree;     //一开始指向二叉树的根部
	previous = temp;     //一开始指向二叉树的根部
	while (temp != NULL)     //若当前位置为NULL，则表示寻找到尽头的合适位置
	{
		previous = temp;     //前一指针指向当前指针，为当前指针移动做好保存
		if (strcmp(temp->name, txl->name) > 0)     //比较两个字符串，二叉树中的字符串较大，则往左走
		{
			temp = temp->lnext;     //当前指针往左走
		}
		else
		{
			temp = temp->rnext;     //否则往右走
		}
	}
	if (strcmp(previous->name, txl->name) > 0)     //temp为NULL了，则previous为找到的合适的位置，需要再比较一次字符串
	{
		previous->lnext=txl;     //若二叉树中的字符串较大，则存放在左边
	}
	else
	{
		previous->rnext=txl;     //若二叉树中的字符串较小，则存放在右边
	}
	return tree;
}
Txl *create_member(Txl *tree)
{    
	int y;//要添加的联系人个数 
	printf("请输入要新增的联系人的个数：\n\n");
	scanf("%d",&y);
	for(int i=0;i<y;i++){
	Txl *s = (Txl *)malloc(sizeof(Txl));     //申请一个结构体
	system("cls");     //清屏
	printf("姓名：");
	scanf("%s",&s->name , 15);     //获取姓名
	printf("电话：");
	scanf("%d",&s->phonenumber);    
	s->lnext = NULL;
	s->rnext = NULL;
	system("cls");
	printf("输入完成！\n\n");
	insert_member(tree, s);  //新申请的插入二叉树
	}   
	return tree;
}
void deleta_m(Txl *father, Txl *current, char input[15], int direction)
{
	if (current == NULL)    //若二叉树为空则返回
	{
		return;
	}
	if (strcmp(current->name, input) == 0)    //若姓名相同则为指定联系人
	{	
		int m;
		printf("查询到的联系人电话：%d\n",current->phonenumber);
		printf("是否为要删除(或修改)的联系人？\n1.是  2.否(继续)\n");
		scanf("%d",&m);
		if(m==0)
		{
			return ;
		}
		if(m==1)
		{
		  
		Txl *temp;    //用于指向二叉树中当前的位置
		Txl *previous;    //用于指向二叉树中前一个的位置
		temp = current->lnext;    //指向当前指针的左向，目的是把当前指针右向插入到左边
		previous = current;    //前一指针指向找到的结构体
		while (temp != NULL)     //若为空则为找到了最右边
		{
			previous = temp;     //前一指针指向当前指针，保存当前位置，以便接下来当前指针往下走
			temp = temp->rnext;    //当前指针指向右边
		}
		previous->rnext = current->rnext;    //前一指针指向为最右的结构体，将要删除的结构体的右指针插入前一指针的右边
		if (direction == 1)    //若为1，则为，指定联系人在二叉树上其前一指针的左边
		{
			father->lnext = current->lnext;    //指定联系人的前一指针的左指针指向指定联系人指针的左边，即跳过了指定联系人
		}
		else
		{
			father->rnext = current->lnext;    //指定联系人的前一指针的右指针指向指定联系人指针的左边，即跳过了指定联系人
		}
		free(current);    //将指定联系人释放
		printf("删除成功\n");
		return ;
		}
	}
	deleta_m(current, current->lnext, input, 1);    //以当前指针、当前指针的左边为参数继续递归，参数1表示方向为左边
	deleta_m(current, current->rnext, input, 2);    //以当前指针、当前指针的右边为参数继续递归，参数2表示方向为右边
}
Txl *deleta_member(Txl *tree)
{
	char input[15];     //存放查询的姓名
	system("cls");
	printf("────────────────────────\n");
		printf("请输入要删除的联系人姓名：");
		scanf("%s", input, 15);     //获取需要查询的姓名
		if (strcmp(tree->name, input) == 0)     //比较二叉树的根的联系人姓名，若要删除的联系人为根部，需要更新根
		{
			Txl *temp;     //指向当前结构体
			Txl *previous;     //指向前一结构体
			Txl *newt;     //为新的根
			temp = tree->lnext;     //当前指针指向根的左边
			printf("删除成功\n");
			if (temp == NULL)
			{
				temp = tree->rnext;
				if (temp == NULL)
				{
					tree->phonenumber = 0;     //空树标志
					free(tree->name);
					return tree;     //若右边也为空，则返回的是空树
				}
				else
				{
					free(tree);     //将原根释放
					return temp;     //若左边为空则直接返回右边，
				}
			}
			else
			{
				newt = tree->lnext;     //以根的左边为新根
				previous = tree;     //前一指针指向根，由于temp不为NULL，必回执行至少一次遍历
				while (temp != NULL)     //若为NULL，则为找到了最右的结构体
				{
					previous = temp;     //当前指针指向temp，为temp继续右移做好保存
					temp = temp->rnext;     //当前指针右移
				}
				previous->rnext = tree->rnext;     //前一指针为新二叉树最右边，将原二叉树右边插入右边
				free(tree);     //将原根释放
				return newt;     //返回新二叉树
			}
		}
		else
		{
			deleta_m(NULL, tree, input, 1);     //删除的非根，调用函数查询删除
			return tree;
		}
}
	

void find_m(Txl *father, Txl *current, char input[15], int direction)
{
	if (current == NULL)    //若二叉树为空则返回
	{
		return;
	}
	if (strcmp(current->name, input) == 0)    //若姓名相同则为指定联系人
	{
		Txl *temp;    //用于指向二叉树中当前的位置
		Txl *previous;    //用于指向二叉树中前一个的位置
		temp = current->lnext;    //指向当前指针的左向，目的是把当前指针右向插入到左边
		previous = current;    //前一指针指向找到的结构体
		while (temp != NULL)     //若为空则为找到了最右边
		{
			previous = temp;     //前一指针指向当前指针，保存当前位置，以便接下来当前指针往下走
			temp = temp->rnext;    //当前指针指向右边
		}
		
		printf("查询成功\n");
		printf("电话：%d\n",current->phonenumber);
		numyes=1;
		
	}
	find_m(current, current->lnext, input, 1);    //以当前指针、当前指针的左边为参数继续递归，参数1表示方向为左边
	find_m(current, current->rnext, input, 2);    //以当前指针、当前指针的右边为参数继续递归，参数2表示方向为右边
}
void find_m_by_p(Txl *father, Txl *current, int x, int direction)
{
	if (current == NULL)    //若二叉树为空则返回
	{
		return;
	}
	if (current->phonenumber == x)    //若姓名相同则为指定联系人
	{
		Txl *temp;    //用于指向二叉树中当前的位置
		Txl *previous;    //用于指向二叉树中前一个的位置
		temp = current->lnext;    //指向当前指针的左向，目的是把当前指针右向插入到左边
		previous = current;    //前一指针指向找到的结构体
		while (temp != NULL)     //若为空则为找到了最右边
		{
			previous = temp;     //前一指针指向当前指针，保存当前位置，以便接下来当前指针往下走
			temp = temp->rnext;    //当前指针指向右边
		}
		
		printf("查询成功\n");
		printf("姓名：%s\n",current->name);
		numyes=1;
		
	}
	find_m_by_p(current, current->lnext, x, 1);    //以当前指针、当前指针的左边为参数继续递归，参数1表示方向为左边
	find_m_by_p(current, current->rnext, x, 2);    //以当前指针、当前指针的右边为参数继续递归，参数2表示方向为右边
}
Txl *find_member(Txl *tree)
{
	char input[15];     //存放查询的姓名
	int x;
	system("cls");
	printf("────────────────────────\n");
		printf("请输入查询方式（姓名或电话）：\n1.姓名  2.电话\n");
		scanf("%d",&x);
		if(x==1)
		{
		printf("请输入要查询的联系人姓名：");
		scanf("%s", input, 15);     
		find_m(NULL, tree, input, 1);    
		} 
		else
		{
		printf("请输入要查询的联系人电话：");
		scanf("%d",&x);
		find_m_by_p(NULL, tree, x, 1);    	
		}
		return tree;
}
	
Txl *modify_member(Txl *tree){
	char p[15];
	printf("要修改的联系人姓名：\n");
	scanf("%s",&p , 15);
	find_m(NULL, tree, p, 1);
	if(numyes==0)
		{
			printf("查询的联系人不存在！\n");
			return tree;
		}
	else
		{
			numyes=0;
		}
	Txl *s = (Txl *)malloc(sizeof(Txl));     //申请一个结构体
	printf("修改后联系人姓名：\n");
	scanf("%s",&s->name , 15);     //获取姓名
	printf("修改后联系人电话：");
	scanf("%d",&s->phonenumber);    
	s->lnext = NULL;
	s->rnext = NULL;
	deleta_m(NULL, tree,p , 1);
	insert_member(tree, s);
	system("cls");
	printf("修改完成！\n\n");
	return tree;
}
void member_printf(Txl *tree)
{
	if (tree == NULL)     //若为NULL则返回
	{
		return;
	}
	member_printf(tree->lnext);     //往左走递归
	if(tree->phonenumber!=0)
	{
		printf("────────────────────────\n");
		printf("姓名：%s", tree->name);  //打印
		printf("          电话：%d\n", tree->phonenumber);  
	}  
	member_printf(tree->rnext);     //往右走递归
	return;
}
void free_tree(Txl *tree)
{
	if (tree == NULL)     //若为NULL则返回
	{
		return;
	}
	free_tree(tree->lnext);     //往左边继续递归
	free_tree(tree->rnext);     //往右边继续递归
	free(tree);     //释放结构体
}

int main(void)
{
	system("color f0");
	int o=0;     //用于存放操作数
	Txl *tree=(Txl *)malloc(sizeof(Txl));      //申请联系人结构体，作为二叉树根
	tree->phonenumber = 0;     //电话为0，表示空树
	tree->lnext = NULL;     //结构体三个指针空指
	tree->rnext = NULL;
	while (1)
	{	
		printf("┌───────────────────────────────┐\n");
		printf("│ 1 添加联系人信息               │\n");
		printf("│ 2 删除联系人信息               │\n");
		printf("│ 3 修改联系人信息               │\n");
		printf("│ 4 查询联系人信息               │\n");
		printf("│ 5 按姓名顺序输出通讯录所有信息 │\n"); 
		printf("│ 6 退出系统                     │\n");
		printf("└───────────────────────────────┘\n");
		printf("    请输入您的操作：");
		scanf("%d", &o);     //获取操作数
		switch (o)
		{
		case 1:tree = create_member(tree); break;     //添加联系人信息
		case 2:tree = deleta_member(tree); break;     //删除联系人信息
		case 3:tree = modify_member(tree); break;     
		case 4:find_member(tree); 
				if(numyes==0)
					{
						printf("查询的联系人不存在！\n");
					}
				else
					{
						numyes=0;
					}
				break;     //全体联系人成绩查询
		case 5:member_printf(tree); break;
		default:break;
		}
		if (o==6)     //跳出死循环
		{
			break;
		}
	}
	free_tree(tree);     //将二叉树释放
	system("pause");
	return 0;
}
