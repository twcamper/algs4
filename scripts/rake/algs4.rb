require 'fileutils'
require 'popen4'
require 'test/unit/assertions'

module Algs4
  BIN_ROOT   = File.expand_path "target/classes"

  class BuildInfo
    attr_reader :target_file
    def initialize(src_file, src_root, bin_root)
      @src_file = src_file
      @target_root  = File.dirname(src_file).sub(src_root, bin_root)
      @package_name = get_package_name(src_file)
      @target_file  = compute_target_file
    end

    def target_dir_for_javac
      if @package_name
        parent_of_package
      else
        @target_root
      end
    end

    private
    def compute_target_file
      class_file = File.basename(@src_file).sub(/\.java$/, ".class")
      if (@package_name)
        class_dir = File.join(parent_of_package, @package_name.gsub(".", File::SEPARATOR))
      else
        class_dir = @target_root
      end
      File.join(class_dir, class_file)
    end

    def get_package_name(file)
      File.read(file)[/^\s*package\s+([^;]+)\s*/]
      $1
    end

    def parent_of_package
      unless @dir
        package_root = @package_name.split(".").first
        @target_root =~ /(.+)\/#{package_root}/
          @dir = $1 || @target_root
      end
      @dir
    end
  end

  def find_changed
    self::FILES.each do |src_file|
      info = BuildInfo.new(src_file, self::SRC, self::BIN)

      if !File.exist?(info.target_file) || File.mtime(src_file) > File.mtime(info.target_file)
        out_dir = info.target_dir_for_javac
        if self::Changed[out_dir]
          self::Changed[out_dir] << src_file
        else
          self::Changed[out_dir] = [src_file]
        end
      end
    end
  end

  def build
    self::Changed.each do |out_dir, src_list|
      FileUtils.mkdir_p(out_dir, :verbose => false)
      run_shell "javac -d #{out_dir} #{src_list.join(' ')}"
    end
  end

  def run_shell(cmd)
    puts cmd = "time " + cmd
    status = POpen4::popen4(cmd) do |stdout, stderr|
      puts stdout.read.strip
      # color the console output of stderr
      puts "\033[0;36m" + stderr.read.strip + "\033[0m"
    end
    raise "Command Failed" unless status.exitstatus == 0
  end

  module Src
    module Main
      module Java
        extend ::Algs4
        SRC          = File.expand_path "src/main/java"
        BIN          = Algs4::BIN_ROOT
        FILES        = Dir["#{SRC}/**/*.java"]
        Changed      = {}

        module Lib
          extend Test::Unit::Assertions
          extend ::Algs4  # include find_changed(), run_shell() as singletons
          SRC   = File.expand_path "src/main/java/lib"
          BIN   = "#{Algs4::BIN_ROOT}/lib"
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
        extend ::Algs4
        SRC     = File.expand_path "src/test/java"
        BIN     = "#{Algs4::BIN_ROOT}/test"
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
