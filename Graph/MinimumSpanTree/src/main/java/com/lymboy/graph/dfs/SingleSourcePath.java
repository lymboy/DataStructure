package com.lymboy.graph.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lymboy.graph.base.Graph;

/**
 * 深度优先遍历
 */
public class SingleSourcePath {

    private Graph G;
    // source
    private int s;
    private boolean[] visited;
    private int[] pre;

    public SingleSourcePath(Graph G, int s) {
        this.G = G;
        this.s = s;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        dfs(s, s);
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
            }
        }
    }

    /**
     * 判断到指定节点是否可达
     * 
     * @param target
     * @return
     */
    public boolean isConnectedTo(int target) {
        G.validateVertex(target);
        return visited[target];
    }

    /**
     * 返回到目标节点的路径
     * 
     * @param target
     * @return
     */
    public Iterable<Integer> path(int target) {
        List<Integer> res = new ArrayList<>();
        if (!isConnectedTo(target)) {
            return res;
        }
        int t = target;
        while (t != s) {
            res.add(t);
            t = pre[t];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph G = new Graph("g.txt");
        SingleSourcePath graphDFS = new SingleSourcePath(G, 0);
        System.out.println(graphDFS.path(5));
    }
}
