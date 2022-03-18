package utils;

public class CircleList<T> {

    /*Node class*/
    private class NodeList {
        protected NodeList previous;
        private T data;
        protected NodeList next;

        public NodeList() {
            this.previous = null;
            this.next = null;
        }

        public NodeList(T received) {
            this.previous = null;
            this.data = received;
            this.next = null;
        }

        protected void setInformation(T received) {
            this.data = received;
        }

        public Object getData() {
            return this.data;
        }
    }

    private NodeList current;
    private NodeList first;
    private NodeList last;

    /*Constructor*/
    public CircleList() {
        /*this.current = new NodeList();*/
        this.first = this.current;
        this.last = this.current;
        this.current.next = this.current;
        this.current.previous = this.current;
    }

    public CircleList(T received) {
        this.current = new NodeList(received);
        this.first = this.current;
        this.last = this.current;
        this.current.next = this.current;
        this.current.previous = this.current;
    }

    public boolean setInformation(T received) {
        if (current == null)
            return false;
        this.current.setInformation(received);
        return true;
    }

    public Object getData() {
        if (current == null)
            return null;
        return this.current.getData();
    }

    public boolean next() {
        if (current == null)
            return false;
        this.current = this.current.next;
        return true;
    }

    public boolean previous() {
        if (current == null)
            return false;
        this.current = this.current.previous;
        return true;
    }

    public Object inNext() {
        if (current == null)
            return null;
        return this.current.next.getData();
    }

    public Object inPrevious() {
        if (current == null)
            return null;
        return this.current.previous.getData();
    }

    private void makingNode(NodeList receivedNode) {
        if (this.current == null) {
            this.current = receivedNode;
            this.first = this.current;
            this.last = this.current;
            this.current.next = this.current;
            this.current.previous = this.current;
        } else {
            receivedNode.next = this.current;
            receivedNode.previous = this.current.previous;
            this.current.previous.next = receivedNode;
            this.current.previous = receivedNode;
            this.last = receivedNode;
            this.current = receivedNode;
        }
    }

    public void add() {
        makingNode(new NodeList());
    }

    public void add(T received) {
        makingNode(new NodeList(received));
    }

    public boolean removeNode() {
        if (this.current == null)
            return false;

        this.current.previous.next = this.current.next;
        this.current.next.previous = this.current.previous;
        if (this.current == this.first) {
            this.first = this.current.next;
            this.current = this.current.next;
        }
        if (this.current == this.last) {
            this.last = this.current.previous;
            this.current = this.current.previous;
        }
        if (this.current.next == this.current) {
            this.current = null;
            this.first = null;
            this.last = null;
        }
        return true;
    }

    public void deleteList() {
        while (this.removeNode()) ;
    }
}
