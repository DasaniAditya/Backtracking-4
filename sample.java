// Time Complexity : O(N * (H*W)! )
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        BuildingPlacement buildingPlacement = new BuildingPlacement();
        
        System.out.println(buildingPlacement.fingMinDistance(4,4,3));
    }
    public static class BuildingPlacement{
        int minDistance;
        public int fingMinDistance(int height, int width, int buildings) {
            int[][] grid = new int[height][width];
            minDistance = Integer.MAX_VALUE;
            for(int i = 0 ; i < height; i++) {
                for(int j = 0 ; j < width; j++) {
                    grid[i][j] = -1;
                }
            }
            
            backtrack(grid, 0 , 0, height, width, buildings);
            return minDistance;
        }
        private void bfs(int[][] grid, int height,int width) {
            Queue<Integer> queue = new LinkedList<>();
            int[][] dirs = new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
            boolean[][] visited = new boolean[height][width];
            for(int i = 0; i < height; i++) {
                for(int j = 0 ; j < width; j++) {
                    if(grid[i][j] == 0) {
                        queue.add(i);
                        queue.add(j);
                        visited[i][j] = true;
                    }
                }
            }
            
            int dist = 0;
            while(!queue.isEmpty()) {
                int sz = queue.size();
                for(int i = 0 ; i < sz/2; i++) {
                    int r = queue.poll();
                    int c = queue.poll();
                    for(int[] dir : dirs) {
                        int nr = r + dir[0];
                        int nc = c + dir[1];
                        if(nr >= 0 && nc >= 0 && nr < height && nc < width && !visited[nr][nc]) {
                            queue.add(nr);
                            queue.add(nc);
                            visited[nr][nc] = true;
                        }
                    }
                }
                dist++;
            }
        minDistance = Math.min(minDistance, dist - 1);
        }
        private void backtrack(int[][] grid, int r,int c, int height,int width,int buildings) {

            if(c == width) {
                r++;
                c = 0;
            }

            if(buildings == 0) {
                bfs(grid,height,width);
                return;
            }
            for(int i = r; i < height; i++) {
                for(int j = c; j < width;j++) {
                    grid[i][j] = 0;
                    backtrack(grid, i , j + 1, height,width, buildings - 1);
                    grid[i][j] = -1;
                }
                c = 0;
            }
        }
    }
}

// Time Complexity : O(N*M)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No




class Solution {
    List<String> result;
    public String[] expand(String s) {
        if(s == null || s.length() == 0) {
            return new String[0];
        }
        result = new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>();
        int i = 0;
        
        while(i < s.length()) {
            List<Character> block = new ArrayList<>();
            if(s.charAt(i) == '{') {
                i++;
                while(s.charAt(i) != '}') {
                    if(s.charAt(i) != ',') {
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            } else {
                block.add(s.charAt(i));
            }
            i++;
            blocks.add(block);
        }
        
        backtrack(blocks, 0, new StringBuilder());
        
        int idx = 0;
        String[] arr = new String[result.size()];
        
        for(String list : result) {
            arr[idx++] = list;
        }
        Arrays.sort(arr);
        return arr;
    }
    
    private void backtrack(List<List<Character>> blocks, int index, StringBuilder sb) {
        
        if(index == blocks.size()) {
            result.add(sb.toString());
            return;
        }
        
        List<Character> block = blocks.get(index);
        for(int j = 0; j < block.size(); j++) {
            sb.append(block.get(j));
            backtrack(blocks, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}