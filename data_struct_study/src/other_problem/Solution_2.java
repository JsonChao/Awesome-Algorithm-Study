package other_problem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 生产者消费者
 */
public class Solution_2 {

    // 1、定义一个Message类
    public class Message {

        private int code;
        private String msg;
//        Handler target;

        public Message() {
        }

        public Message(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    // 2、定义一个IMessageQueue接口：包含next与enqueueMessage抽象方法
    public interface IMessageQueue {

        Message next() throws InterruptedException;

        void enqueueMessage(Message message) throws InterruptedException;
    }

    // 3、阻塞队列LinkedBlockingQueue实现阻塞式生产者消费者模式：take/put 是阻塞方法
    public class MessageQueue implements IMessageQueue {

        private final BlockingQueue<Message> queue;

        public MessageQueue(int cap) {
            this.queue = new LinkedBlockingQueue<>(cap);
        }

        public Message next() throws InterruptedException {
            return queue.take();
        }

        public void enqueueMessage(Message message) {
            try {
                queue.put(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 4、链表 + synchronized + wait/notifyAll + volatile变量 + 对象锁实现非阻塞式的生产者消费者模式
    public class MessageQueue1 implements IMessageQueue {

        private Queue<Message> queue;
        private volatile int count;
        private final Object BUFFER_LOCK = new Object();

        public MessageQueue1(int cap) {
            this.count = cap;
            queue = new LinkedList<>();
        }

        @Override
        public Message next() throws InterruptedException {
            synchronized (BUFFER_LOCK) {
                while (queue.size() == 0) {
                    BUFFER_LOCK.wait();
                }
                Message message = queue.poll();
                BUFFER_LOCK.notifyAll();
                return message;
            }
        }

        @Override
        public void enqueueMessage(Message message) throws InterruptedException {
            synchronized (BUFFER_LOCK) {
                while (queue.size() == count) {
                    BUFFER_LOCK.wait();
                }
                queue.offer(message);
                BUFFER_LOCK.notifyAll();
            }
        }
    }

}
