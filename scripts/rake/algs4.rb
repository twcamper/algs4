require 'test/unit/assertions'
require File.expand_path 'scripts/rake/algs4/build_info'
require File.expand_path 'scripts/rake/algs4/static_methods'

module Algs4
  BIN_ROOT   = File.expand_path "target/classes"

  module Src
    module Main
      module Java
        extend ::Algs4::StaticMethods
        SRC          = File.expand_path "src/main/java"
        BIN          = ::Algs4::BIN_ROOT
        FILES        = Dir["#{SRC}/**/*.java"]
        Changed      = {}

        module Lib
          extend ::Algs4::StaticMethods
          SRC   = File.expand_path "src/main/java/lib"
          BIN   = "#{::Algs4::BIN_ROOT}/lib"
          FILES = Dir["#{SRC}/**/*.java"]
          Changed = {}

          def self.package
            jar_file = "#{BIN}.jar"
            if (!Changed.empty? || !File.exists?(jar_file))
              # change dir to get correct depth in file names
              Dir.chdir(BIN)
              class_file_list = Dir["**/*.class"]
              run_shell "jar cf #{jar_file} #{class_file_list.join(' ')}"
            end
          end
        end
      end
    end

    module Test
      module Java
        extend ::Algs4::StaticMethods
        SRC     = File.expand_path "src/test/java"
        BIN     = "#{::Algs4::BIN_ROOT}/test"
        FILES   = Dir["#{SRC}/**/*.java"]
        Changed = {}

        def self.run_tests(test)
          tests = Dir["#{BIN}/**/*Test.class"]
          classpaths = tests.collect do |f|
            File.dirname(File.dirname(f))
          end.uniq
          classes = tests.collect do |f|
            f.split(File::SEPARATOR).last(2).join(".").sub(/\.class$/, "")
          end
          run_shell "java -cp $CLASSPATH:#{classpaths.join(':')} org.junit.runner.JUnitCore #{classes.join(' ')}"
        end

        def self.package_testable
          jar_file = "#{::Algs4::BIN_ROOT}/tested_chapter_classes.jar"
          tested_class_files = Dir["#{SRC}/ch*/**/*.java"].collect do |src_file|
            test = BuildInfo.new(src_file, SRC, BIN).target_file
            test.sub(/\/test\//, "/").sub(/Test\.class$/, ".class")
          end

          # don't repackage if no classes are newer than the jar
          return unless new_package_needed(jar_file, tested_class_files)

          package_paths = tested_class_files.collect do |f|
            f.sub("#{::Algs4::BIN_ROOT}/", "")
          end

          # so the paths are found
          Dir.chdir ::Algs4::BIN_ROOT

          run_shell "jar cf #{jar_file} #{package_paths.join(' ')}"
        end

        def self.new_package_needed(jar, classes)
          return true if !File.exists? jar
          classes.any? do |f|
            !File.exists?(f) || (File.mtime(f) > File.mtime(jar))
          end
        end

      end
    end
  end
end
