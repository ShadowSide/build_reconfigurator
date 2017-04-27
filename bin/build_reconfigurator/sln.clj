(ns build-reconfigurator.sln)

(defn parse-sln-proj-line [line]
   (rest (flatten (re-seq 
     #"^\s*Project\s*\(\s*\"\{[0-9A-F]{8}-[0-9A-F]{4}-[0-9A-F]{4}-[0-9A-F]{4}-[0-9A-F]{12}\}\"\s*\)\s*=\s*\"(.+)\"\s*,\s*\"(.+)\"\s*,\s*\"\{[0-9A-F]{8}-[0-9A-F]{4}-[0-9A-F]{4}-[0-9A-F]{4}-[0-9A-F]{12}\}\"\s*$" line))))

(defn parse-sln [lines]
  (filter (comp not empty?) (map parse-sln-proj-line lines))) 
     
(defn parse-sln-file [slnFilePath] 
  (with-open [reader (clojure.java.io/reader slnFilePath)] 
    (vec (parse-sln (line-seq reader)))))