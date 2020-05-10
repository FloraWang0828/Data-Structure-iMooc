public class Array {
    private int[] data;//数组
    private int size;//数组的实际容量
    //构造函数，传入数组的容量capacity构造Array
    public Array(int capacity){
        data = new int[capacity];
        size = 0;
    }
    //无参数的构造函数，默认数组的容量capacity=10
    public Array(){
        //data = new int[10];
        //size = 0;
        //可以简化写法
        this(10);
    }
    //获取数组中的元素个数
    public int getCapacity(){
        return data.length;
    }
    //获取数组的容量
    public int getSize(){
        return size;
    }
    //返回数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }
    //向所有元素后添加一个新元素
    public void addLast(int e){
        //检查数组是否还有剩余空间
        if(size == data.length)
            throw new IllegalArgumentException("AddLast failed. Array is full.");
        data[size] = e;
        size++;
    }
    //向数组的头添加一个新元素
    public void addFirst(int e){
        add(0, e);
    }
    //在第index个位置插入一个新元素e
    public void add(int index, int e){
        //检查数组是否还有剩余空间
        if(size == data.length)
            throw new IllegalArgumentException("AddLast failed. Array is full.");
        //index要合法
        if(index < 0 || index > size)
            throw new IllegalArgumentException("AddLast failed.");
        for(int i = size - 1 ; i >=index ; i--){
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }
    //覆盖父类的方法
    @Override
    public String toString(){
        //拼接字符串

    }
}