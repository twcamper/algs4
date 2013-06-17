VERSION_NUMBER = "1.0.0"
GROUP = "algs4"
COPYRIGHT = ""

repositories.remote << "http://repo1.maven.org/maven2"

TEXTBOOK_JARS = ["~/cs/algs4/third_party/stdlib.jar", "~/cs/algs4/third_party/algs4.jar"]
ALGS4_CLASSPATH = ["."] + TEXTBOOK_JARS

desc 'Exercise repo for Algorithms, Fourth Edition by Sedgewick and Wayne'
define "algs4" do

  project.version = VERSION_NUMBER
  project.group = GROUP
  manifest['Copyright'] = COPYRIGHT

  compile.with ALGS4_CLASSPATH
end
