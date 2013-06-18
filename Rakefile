require 'rake/clean'
require 'fileutils'
require 'popen4'
require 'test/unit/assertions'

module Algs4
  module Build
    Changed = {}
  end
end

SRC_DIR = File.expand_path "src/main/java"
BIN_DIR = File.expand_path "target/classes"
directory BIN_DIR

# file lists
SRC   = FileList["#{SRC_DIR}/**/*.java"]

# clobber and clean lists
CLEAN.include(FileList["#{BIN_DIR}/**/*.class"])

desc "find changed src files and group by directory"
task :find_changed do
  SRC.each do |src_file|
    out_file = src_file.sub(SRC_DIR, BIN_DIR).sub(/\.java$/,".class")
    if !File.exist?(out_file) || File.mtime(src_file) > File.mtime(out_file)
      out_dir = File.dirname(out_file)
      if Algs4::Build::Changed[out_dir]
        Algs4::Build::Changed[out_dir] << src_file
      else
        Algs4::Build::Changed[out_dir] = [src_file]
      end
    end
  end
end

task :all do
  Algs4::Build::Changed.each do |out_dir, src_list|
    mkdir_p out_dir, :verbose => false

    # Seems sufficient to have the classpath set in the env
    # puts cmd = "time javac -cp $CLASSPATH -d #{out_dir} #{src_list.join(' ')}"
    #
    puts cmd = "time javac -d #{out_dir} #{src_list.join(' ')}"
    POpen4::popen4(cmd) do |stdout, stderr|
      puts stdout.read.strip
      puts stderr.read.strip
    end
  end
end

task :all => [:check_classpath, :find_changed]
task :default => :all

desc "warn and exit if no classpath"
task :check_classpath do
  include Test::Unit::Assertions
  script =  "#{ENV['HOME']}/cs/algs4/scripts/set_classpath"
  File.read(script)[/CLASSPATH=".*\$HOME([^\"]+)/]
  cp = $1

  assert(cp && !cp.empty?, "bad read of script: #{script}")
  assert_match(ENV['CLASSPATH'], /#{Regexp.escape(cp)}/,  "class path not set correctly")
end
