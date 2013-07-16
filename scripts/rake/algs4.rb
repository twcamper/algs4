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

      end
    end
  end
end
