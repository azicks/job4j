package ru.job4j.lambda;

import java.util.*;

public class Departments {
    public static final class Org implements Comparable<Org> {
        private final List<String> deps;

        public Org(List<String> deps) {
            this.deps = deps;
        }

        public List<String> getDeps() {
            return deps;
        }

        @Override
        public int compareTo(Org o) {
            int len = Integer.min(this.getDeps().size(), o.getDeps().size());
            int diff = 0;
            for (int i = 0; i < len; i++) {
                diff = this.getDeps().get(i).compareTo(o.getDeps().get(i));
                if (diff != 0) {
                    break;
                }
            }
            return diff;
        }

        @Override
        public String toString() {
            return deps.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Org org = (Org) o;
            return Objects.equals(deps, org.deps);
        }

        @Override
        public int hashCode() {
            return Objects.hash(deps);
        }
    }

    public List<Org> convert(List<String> deps) {
        List<Org> result = new ArrayList<>();
        deps.forEach(dep -> {
            String[] o = dep.split("/");
            List<String> depts = new ArrayList<>();
            for (String d : o) {
                depts.add(d);
                result.add(new Org(new ArrayList<>(depts)));
            }
        });
        return result;
    }

    public List<Org> sortAsc(List<Org> orgs) {
        Collections.sort(orgs);
        return orgs;
    }

    public List<Org> sortDesc(List<Org> orgs) {
        Collections.sort(orgs, Collections.reverseOrder());
        return orgs;
    }
}