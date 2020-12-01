package other_problem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution_2 {

    public class Message {
        private int code;
        private String msg;
//        Handler target;

        public Message() { }
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


    public interface IMessageQueue {
        Message next() throws InterruptedException;
        void enqueueMessage(Message message) throws InterruptedException;
    }

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

    public class MessageQueue1 implements IMessageQueue {

        private Queue<Message> queue;
        private final AtomicInteger integer = new AtomicInteger(0);
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
