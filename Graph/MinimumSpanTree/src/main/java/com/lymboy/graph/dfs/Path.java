package com.lymboy.graph.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lymboy.graph.base.Graph;

/**
 * 深度优先遍历
 */
public class Path {

    private Graph G;
    // source
    private int s;
    private int t;
    private boolean[] visited;
    private int[] pre;

    public Path(Graph G, int s, int t) {
        G.validateVertex(s);
        G.validateVertex(t);

        this.G = G;
        this.s = s;
        this.t = t;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        dfs(s, s);
    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        if (v == t) {
            return true;
        }
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断到指定节点是否可达
     * 
     * @param target
     * @return
     */
    public boolean isConnected() {
        return visited[t];
    }

    /**
     * 返回到目标节点的路径
     * 
     * @param target
     * @return
     */
    public Iterable<Integer> path() {
        List<Integer> res = new ArrayList<>();
        if (!isConnected()) {
            return res;
        }
        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph G = new Graph("g.txt");
        Path graphDFS = new Path(G, 0, 6);
        System.out.println(graphDFS.path());
    }
}
