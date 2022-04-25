package com.lymboy.graph.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lymboy.graph.base.Graph;

/**
 * 联通分量
 */
public class ConnectedComponent {

    private Graph G;
    private int[] visited;
    private int cccount = 0;
    private List<Integer> order = new ArrayList<>();

    public ConnectedComponent(Graph G) {
        this.G = G;
        visited = new int[G.V()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }
        for (int v = 0; v < G.V(); v++) {
            if (visited[v] == -1) {
                dfs(v, cccount);
                cccount++;
            }
        }
    }

    public int count() {
        for (int v : visited) {
            System.out.print(v + " ");
        }
        System.out.println();
        return cccount;
    }

      /**
     * 图的先序遍历
     * @return
     */
    public Iterable<Integer> order() {
        return order;
    }

    private void dfs(int v, int ccid) {
        visited[v] = ccid;
        order.add(v);
        for (Integer w : G.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, ccid);
            }
        }
    }

    public boolean isConnected(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    @SuppressWarnings("unchecked")
    public List<Integer>[] components() {
        List<Integer>[] res = new ArrayList[cccount];
        for (int i = 0; i < res.length; i++) {
            res[i] = new ArrayList<>();
        }
        for (int i = 0; i < visited.length; i++) {
            res[visited[i]].add(i);
        }
        return res;
    }

    public static void main(String[] args) {
        Graph G = new Graph("g.txt");
        ConnectedComponent cc = new ConnectedComponent(G);
        System.out.println(cc.order());
        System.out.println(cc.count());
        System.out.println(cc.isConnected(0, 5));
        System.out.println(cc.isConnected(0, 6));
        System.out.println(Arrays.deepToString(cc.components()));
    }
}
