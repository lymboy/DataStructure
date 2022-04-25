package com.lymboy.graph.dfs;

import java.util.ArrayList;
import java.util.List;

import com.lymboy.graph.base.Graph;

/**
 * 深度优先遍历
 */
public class GraphDFS {

    private Graph G;
    private boolean[] visited;
    private List<Integer> pre = new ArrayList<>();
    private List<Integer> post = new ArrayList<>();

    public GraphDFS(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    /**
     * 图的先序遍历
     * @return
     */
    public Iterable<Integer> pre() {
        return pre;
    }

    /**
     * 图的后序遍历
     * @return
     */
    public Iterable<Integer> post() {
        return post;
    }

    private void dfs(int v) {
        visited[v] = true;
        pre.add(v);
        for (Integer w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
        post.add(v);
    }

    public static void main(String[] args) {
        Graph G = new Graph("g.txt");
        GraphDFS graphDFS = new GraphDFS(G);
        System.out.println(graphDFS.pre());
        System.out.println(graphDFS.post());
    }
}
