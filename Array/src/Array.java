public class Array<E> {//支持泛型

    private E[] data;//数组
    private int size;//数组的实际容量

    //构造函数，传入数组的容量capacity构造Array
    public Array(int capacity){
        data = (E[])new Object[capacity];
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

    //在第index个位置插入一个新元素e
    public void add(int index, E e){
    /*
        //检查数组是否还有剩余空间
        if(size == data.length)
            throw new IllegalArgumentException("AddLast failed. Array is full.");

     */
        //index要合法
        if(index < 0 || index > size)
            throw new IllegalArgumentException("AddLast failed.");

        //数组空间的扩容(如果数组满了，不报满error，直接扩容添加元素)
        if(size == data.length){
            resize(2 * data.length);
        }

        for(int i = size - 1 ; i >=index ; i--){
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    //向所有元素后添加一个新元素
    public void addLast(E e){
        //检查数组是否还有剩余空间
        if(size == data.length)
            throw new IllegalArgumentException("AddLast failed. Array is full.");

        data[size] = e;
        size++;
    }

    //向数组的头添加一个新元素
    public void addFirst(E e){
        add(0, e);
    }

    //获取index索引位置的元素
    E get(int index){
        //要保证index的合法性
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Index is illegal!");
        }

        return data[index];
    }

    //修改index索引位置的元素为e
    void set(int index, E e){
        //要保证index的合法性
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Index is illegal!");
        }

        data[index] = e;
    }

    //查找数组中是否有元素e
    public boolean contains(E e){
        for(int i = 0 ; i < size ; i++){
            if(data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    //查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e){
        for(int i = 0 ; i < size ; i++){
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    //从数组中删除index位置的元素，返回删除的元素
    public E remove(int index){
        //boundary case
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Index is illegal!");
        }

        E ret = data[index];//保存待删除元素
        for(int i = index + 1 ; i < size ; i++){
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;//loitering objects != memory leak

        //数组长度过小，数组空间动态缩小
        if(size == data.length / 2){
            resize(data.length / 2);
        }
        return ret;
    }

    //从数组中删除第一个元素，返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    //从数组中删除最后一个元素，返回删除的元素
    public E removeLast(){
        return remove(size - 1);
    }

    //从数组中删除元素e
    public boolean removeElement(E e){
        int index = find(e);
        if(index != -1){
            remove(index);
            return true;
        }
        return false;
    }

    @Override //覆盖父类的方法
    public String toString(){
        //拼接字符串
        StringBuilder res = new StringBuilder();
        //明确输出字符串的格式
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for(int i = 0 ; i < size ; i++){
            res.append(data[i]);
            //**注意数组中的格式**
            if(i != size - 1){
                res.append(", ");
            }
        }
        res.append(']');

        return res.toString();
    }

    private void resize(int newCapacity){
        //新的容量数组
        E[] newData = (E[])new Object[newCapacity];
        for(int i = 0 ; i < size ; i++){
            newData[i] = data[i];
        }
        data = newData;
    }
}