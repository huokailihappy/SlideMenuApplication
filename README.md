# SlideMenuApplication
联系人列表，房间号列表，显示前两位，支持数字字母检索，支持快速滑动，采用RecycleView展示数据，支持下拉刷新下拉加载更多，支持添加搜索头部，还可以根据自己的需要添加头部尾部。
___效果展示

![image](https://raw.githubusercontent.com/huokailihappy/SlideMenuApplication/master/SlideMenuApplication/pic/pic1.png)


____比较代码，在这里可以修改比较的前几位

```
//这里比较的是前两位
public int compare(String o1, String o2) {
int c1 = (o1.charAt(0) + "").toUpperCase().hashCode();
int c11 = '#', c21 = '#';
if (o1.length() > 1) {
c11 = (o1.charAt(1) + "").toUpperCase().hashCode();
}
int c2 = (o2.charAt(0) + "").toUpperCase().hashCode();
if (o1.length() > 1) {
c21 = (o2.charAt(1) + "").toUpperCase().hashCode();
}

boolean c1Flag = notChara(c1); // 不是字母
boolean c2Flag = notChara(c2); // 不是字母
if (c1Flag && !c2Flag) {
return 1;
} else if (!c1Flag && c2Flag) {
return -1;
}
if (c1 == c2) {
return c11 - c21;
} else {
return c1 - c2;
}
```
