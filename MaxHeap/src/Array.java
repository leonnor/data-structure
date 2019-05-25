/**
 * className Array
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/2 20:53
 */
public class Array<E> {

    private E[] data;
    private int size;

    /**
     * 构造函数，传入数组的容量capacity构造Array
     * @param capacity
     */
    public Array(int capacity){
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 无参构造函数，默认数组的容量capacity = 10
     */
    public Array(){
        this(10);
    }

    public Array(E[] arr){
        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++){
            data[i] = arr[i];
        }
        size = arr.length;
    }

    /**
     * 获取数组元素个数
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 获取数组容量
     * @return
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * 返回数组是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 在所有元素后添加一个元素
     * @param e
     */
    public void addLast(E e){

        if (size == data.length){
            resize(2 * data.length);
        }

        data[size] = e;
        size++;
    }

    /**
     * 在第index位置插入一个新元素e
     * @param index
     * @param e
     */
    public void add(int index, E e){

        if (size == data.length){
            resize(2 * data.length);
        }

        if (index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Require index >= 0 and <= size.");
        }

        for (int i = size - 1; i >= index; i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 获取index索引位置的元素
     * @param index
     * @return
     */
    E get(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    public E getLast(){
        return get(size - 1);
    }

    public E getFirst(){
        return get(0);
    }

    /**
     * 修改index索引位置元素为e
     * @param index
     * @param e
     */
    void set(int index, E e){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        data[index] = e;
    }

    /**
     * 查找数组中是否有元素e
     * @param e
     * @return
     */
    public boolean contains(E e){
        for (int i = 0; i < size; i++){
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素e所在索引，不存在返回-1
     * @param e
     * @return
     */
    public int find(E e){
        for (int i = 0; i < size; i++){
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除index位置的元素，返回删除的元素
     * @param index
     * @return
     */
    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++){
            data[i - 1] = data[i];
        }
        size--;

        if (size == data.length / 2){
            resize(data.length / 2);
        }
        return ret;
    }

    /**
     * 删除数组第一个元素
     * @return
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 删除数组最后一个元素
     * @return
     */
    public E removeLast(){
        return remove(size - 1);
    }

    /**
     * 从数组中删除元素e
     * @param e
     */
    public void removeElement(E e){
        int index = find(e);
        if (index != -1){
            remove(index);
        }
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++){
            res.append(data[i]);
            if (i != size - 1){
                res.append(",");
            }
        }
        res.append(']');
        return res.toString();
    }

    /**
     * 实现动态数组扩容的方法
     * @param newCapacity
     */
    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++){
            newData[i] = data[i];
        }
        data = newData;
    }

    public void swap(int i, int j){

        if (i < 0 || i >= size || j < 0 || j >= size){
            throw new IllegalArgumentException("Index is illegal.");
        }

        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }
}
